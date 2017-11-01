package com.rogge.order.model;

import javax.persistence.*;

public class Order {
    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "order_user_name")
    private String orderUserName;

    @Column(name = "order_user_id")
    private Long orderUserId;

    /**
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return product_id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return order_user_name
     */
    public String getOrderUserName() {
        return orderUserName;
    }

    /**
     * @param orderUserName
     */
    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    /**
     * @return order_user_id
     */
    public Long getOrderUserId() {
        return orderUserId;
    }

    /**
     * @param orderUserId
     */
    public void setOrderUserId(Long orderUserId) {
        this.orderUserId = orderUserId;
    }
}