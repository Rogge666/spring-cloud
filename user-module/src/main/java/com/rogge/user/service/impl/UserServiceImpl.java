package com.rogge.user.service.impl;

import com.rogge.common.core.AbstractService;
import com.rogge.user.mapper.UserMapper;
import com.rogge.user.model.User;
import com.rogge.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


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
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
