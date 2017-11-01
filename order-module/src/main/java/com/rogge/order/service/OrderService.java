package com.rogge.order.service;


import com.rogge.common.core.Service;
import com.rogge.order.model.Order;

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
public interface OrderService extends Service<Order> {
    List<Order> getOrderByUserName(String userName);
}
