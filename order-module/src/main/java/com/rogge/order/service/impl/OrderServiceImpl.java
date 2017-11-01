package com.rogge.order.service.impl;

import com.rogge.common.core.AbstractService;
import com.rogge.order.mapper.OrderMapper;
import com.rogge.order.model.Order;
import com.rogge.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
@Service
@Transactional
public class OrderServiceImpl extends AbstractService<Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> getOrderByUserName(String userName) {
        return orderMapper.selectOrderByUserName(userName);
    }
}
