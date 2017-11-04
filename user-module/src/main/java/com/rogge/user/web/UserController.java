package com.rogge.user.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.rogge.common.core.ApiResponse;
import com.rogge.common.core.BaseController;
import com.rogge.user.model.User;
import com.rogge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/10/07
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public ApiResponse add(User user) {
        userService.save(user);
        return ApiResponse.creatSuccess();
    }

    @PostMapping("/delete")
    public ApiResponse delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ApiResponse.creatSuccess();
    }

    @PostMapping("/update")
    public ApiResponse update(User user) {
        userService.update(user);
        return ApiResponse.creatSuccess();
    }

    @GetMapping("/detail")
    public User detail(@RequestParam Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.creatSuccess(pageInfo);
    }

}
