package com.rogge.user.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rogge.common.core.ApiResponse;
import com.rogge.common.core.BaseController;
import com.rogge.common.model.User;
import com.rogge.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
 * @author Created by Rogge on 2017/10/07
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

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

    @GetMapping("/login")
    public ApiResponse login(HttpSession session, @RequestParam Integer id) {
        User lUser = userService.findById(id);
        mSessionUserInfo.setSessionUser(session, lUser);
        return ApiResponse.creatSuccess((Object) "登录成功");
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
