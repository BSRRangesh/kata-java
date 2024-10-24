package com.online.bookstore.order.service.impl;

import com.online.bookstore.book.exception.BookNotFoundException;
import com.online.bookstore.book.repository.BookRepository;
import com.online.bookstore.order.dto.OrderDetailDTO;
import com.online.bookstore.order.dto.OrderRequestDTO;
import com.online.bookstore.order.dto.OrderResponseDTO;
import com.online.bookstore.order.entity.Order;
import com.online.bookstore.order.entity.OrderDetail;
import com.online.bookstore.order.exception.OrderNotFoundException;
import com.online.bookstore.order.repository.OrderRepository;
import com.online.bookstore.order.service.OrderService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        Order order = new Order();
        order.setUserId(orderRequestDTO.getUserId());
        order.setStatus("PLACED");

        List<OrderDetail> orderDetails = orderRequestDTO.getOrderDetails()
                .stream().map(itemDto -> constructOrderDetailEntity(itemDto, order)).toList();
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);

        orderResponseDTO.setOrderId(order.getOrderId());
        orderResponseDTO.setTotalAmount(order.getTotalAmount());
        BeanUtils.copyProperties(orderRequestDTO, orderResponseDTO);

        logger.info("Order created successfully with order id: {} for userId: {}", order.getOrderId(), order.getUserId());
        return orderResponseDTO;
    }

    @Override
    public OrderResponseDTO getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order id " + orderId + " not found"));
        return constructOrderResponseDTO(order);
    }

    @Override
    public Collection<OrderResponseDTO> getAllOrders() {
        Collection<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::constructOrderResponseDTO).toList();
    }

    private OrderResponseDTO constructOrderResponseDTO(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        BeanUtils.copyProperties(order, orderResponseDTO);
        List<OrderDetailDTO> orderDetailDTOs = order.getOrderDetails().stream().map(this::constructOrderDTO).toList();
        orderResponseDTO.setOrderDetails(orderDetailDTOs);
        return orderResponseDTO;
    }

    private OrderDetail constructOrderDetailEntity(OrderDetailDTO itemDto, Order order) {
        OrderDetail orderItem = new OrderDetail();
        orderItem.setBook(bookRepository.findById(itemDto.getBookId()).orElseThrow(() ->
                new BookNotFoundException("Book id " + itemDto.getBookId() + " not found")));
        orderItem.setQuantity(itemDto.getQuantity());
        order.setTotalAmount(order.getTotalAmount() + orderItem.getBook().getPrice() * orderItem.getQuantity());
        orderItem.setOrder(order);
        return orderItem;
    }

    private OrderDetailDTO constructOrderDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        BeanUtils.copyProperties(orderDetail, orderDetailDTO);
        orderDetailDTO.setBookId(orderDetail.getBook().getBookId());
        return orderDetailDTO;
    }

}
