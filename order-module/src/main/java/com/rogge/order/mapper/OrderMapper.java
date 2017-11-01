package com.rogge.order.mapper;

import com.rogge.common.core.Mapper;
import com.rogge.order.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper extends Mapper<Order> {
    List<Order> selectOrderByUserName(String userName);
}