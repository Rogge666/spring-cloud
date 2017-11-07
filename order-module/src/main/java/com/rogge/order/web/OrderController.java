package com.rogge.order.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rogge.common.core.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rogge.common.model.User;
import com.rogge.order.feign.UserFeign;
import com.rogge.order.model.Order;
import com.rogge.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/11/01
 * @since 1.0.0
 */
@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    @Resource
    private RestTemplate mRestTemplate;

    @Resource
    private UserFeign mUserFeign;

    @Value("${person.name}")
    String name;

    @PostMapping("/add")
    public ApiResponse add(Order order) {
        orderService.save(order);
        return ApiResponse.creatSuccess();
    }

    @PostMapping("/delete")
    public ApiResponse delete(@RequestParam Integer id) {
        orderService.deleteById(id);
        return ApiResponse.creatSuccess();
    }

    @PostMapping("/update")
    public ApiResponse update(Order order) {
        orderService.update(order);
        return ApiResponse.creatSuccess();
    }

    @PostMapping("/detail")
    public ApiResponse detail(@RequestParam Integer id) {
        Order order = orderService.findById(id);
        return ApiResponse.creatSuccess(order);
    }

    @GetMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Order> list = orderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.creatSuccess(pageInfo);
    }

    /**
     * 测试mRestTemplate
     * @param userId
     * @return
     */
    @GetMapping("/getOrderByUserId")
    @HystrixCommand(fallbackMethod = "getOrderByUserNameError")
    public ApiResponse getOrderByUserId(@RequestParam("userId") Long userId) {
        System.out.println("==========" + name);
        User lUser = mRestTemplate.getForObject("http://user-module/user/detail?id=" + userId, User.class);
        String userName = lUser.getUsername();
        List<Order> lOrders = orderService.getOrderByUserName(userName);
        return ApiResponse.creatSuccess(lOrders);
    }

    /**
     * 测试feign
     * @param userId
     * @return
     */
    @GetMapping("/getOrderByUserIdV2")
    @HystrixCommand(fallbackMethod = "getOrderByUserNameError")
    public ApiResponse getOrderByUserIdV2(@RequestParam("userId") Long userId) {
        User lUser = mUserFeign.detail(userId);
        String userName = lUser.getUsername();
        List<Order> lOrders = orderService.getOrderByUserName(userName);
        return ApiResponse.creatSuccess(lOrders);
    }

    /**
     * 测试session透传  需要先调用user模块的登录
     * @return
     */
    @GetMapping("/getOrderByUserIdV3")
    public ApiResponse getOrderByUserIdV3() {
        User lUser = mSessionUserInfo.getCurrentSessionUser(User.class);
        String userName = lUser.getUsername();
        List<Order> lOrders = orderService.getOrderByUserName(userName);
        return ApiResponse.creatSuccess(lOrders);
    }

    public ApiResponse getOrderByUserNameError(Long userId) {
        return ApiResponse.creatFail(ResponseCode.Base.API_ERR);
    }

}
