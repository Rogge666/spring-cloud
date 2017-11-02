package com.rogge.order.web;

import com.netflix.discovery.EurekaClient;
import com.rogge.common.core.ApiResponse;
import com.rogge.common.core.ApiResponseVO;
import com.rogge.common.core.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rogge.order.model.Order;
import com.rogge.order.service.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
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
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Resource
    private OrderService orderService;

    @Resource
    private RestTemplate mRestTemplate;

    @Qualifier("eurekaClient")
    @Resource
    private EurekaClient eurekaClient;

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

    @PostMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Order> list = orderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.creatSuccess(pageInfo);
    }

    @GetMapping("/getOrderByUserId")
    public ApiResponse getOrderByUserName(@RequestParam("userId") int userId) {
        ApiResponseVO lApiResponseVO = mRestTemplate.getForObject("http://user-module/rogge/user/detail?id=" + userId, ApiResponseVO.class);
        String userName = (String) ((LinkedHashMap)lApiResponseVO.getData()).get("username");
        List<Order> lOrders = orderService.getOrderByUserName(userName);
        return ApiResponse.creatSuccess(lOrders);
    }

}
