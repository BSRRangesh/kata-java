package com.online.bookstore.order.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderRequestDTO {

    @NotNull(message = "User Id is a mandatory field")
    private String userId;

    private List<OrderDetailDTO> orderDetails;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
