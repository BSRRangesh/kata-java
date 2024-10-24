package com.online.bookstore.order.controller;

import com.online.bookstore.order.dto.OrderRequestDTO;
import com.online.bookstore.order.dto.OrderResponseDTO;
import com.online.bookstore.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerMockTest {


    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private OrderRequestDTO orderRequestDTO;
    private OrderResponseDTO orderResponseDTO;

    @BeforeEach
    public void setUp() {
        orderRequestDTO = new OrderRequestDTO();
        orderResponseDTO = new OrderResponseDTO();
    }

    @Test
    public void testCreateOrder() {
        when(orderService.createOrder(orderRequestDTO)).thenReturn(orderResponseDTO);

        ResponseEntity<OrderResponseDTO> response = orderController.createOrder(orderRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderResponseDTO, response.getBody());
    }

    @Test
    public void testGetOrder() {
        Long orderId = 1L;
        when(orderService.getOrder(orderId)).thenReturn(orderResponseDTO);

        ResponseEntity<OrderResponseDTO> response = orderController.getOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderResponseDTO, response.getBody());
    }

    @Test
    public void testGetAllOrders() {
        Collection<OrderResponseDTO> orders = Collections.singletonList(orderResponseDTO);
        when(orderService.getAllOrders()).thenReturn(orders);

        ResponseEntity<Collection<OrderResponseDTO>> response = orderController.getAllOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

}
