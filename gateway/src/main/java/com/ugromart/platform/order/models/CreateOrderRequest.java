package com.ugromart.platform.order.models;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CreateOrderRequest {
    private String orderId;
    private long userId;
    @NotBlank(message = "orderDate is required")
    private String orderDate;
    private Money totalOrder;
    private  String status;
    private  String customerEmail;
    private List<OrderItem> orderItems;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(String orderId, long userId, String orderDate, Money totalOrder, String status, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalOrder = totalOrder;
        this.status = status;
        this.orderItems = orderItems;
    }

    public CreateOrderRequest(String orderId, long userId, String orderDate, Money totalOrder, String status, String customerEmail, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalOrder = totalOrder;
        this.status = status;
        this.customerEmail = customerEmail;
        this.orderItems = orderItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Money getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Money totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
