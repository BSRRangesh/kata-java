package com.online.bookstore.order.controller;

import com.online.bookstore.order.dto.OrderRequestDTO;
import com.online.bookstore.order.dto.OrderResponseDTO;
import com.online.bookstore.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO  = orderService.createOrder(orderRequestDTO);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable(name= "orderId") @NotNull Long orderId) {
        OrderResponseDTO orderResponseDTO = orderService.getOrder(orderId);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Collection<OrderResponseDTO>> getAllOrders() {
        Collection<OrderResponseDTO> orderResponseDTO = orderService.getAllOrders();  
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
    }
}
