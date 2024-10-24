package com.online.bookstore.order.service;

import com.online.bookstore.order.dto.OrderRequestDTO;
import com.online.bookstore.order.dto.OrderResponseDTO;

import java.util.Collection;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO getOrder(Long orderId);

    Collection<OrderResponseDTO> getAllOrders();
}
