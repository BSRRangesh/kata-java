package com.online.bookstore.cart.service.impl;

import com.online.bookstore.book.repository.BookRepository;
import com.online.bookstore.cart.dto.CartRequestDTO;
import com.online.bookstore.cart.entity.Cart;
import com.online.bookstore.cart.exception.CartItemNotFoundException;
import com.online.bookstore.cart.repository.CartRepository;
import com.online.bookstore.cart.service.CartService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Transactional
public class CartServiceImpl implements CartService {

    private final static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<CartRequestDTO> getCart(String userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);

        if(carts == null || carts.isEmpty()) {
            logger.warn("No items found in cart for user: {}", userId);
            return List.of();
        }
        return carts.stream().map(this::constructCartRequestDTO).collect(Collectors.toList());
    }

    @Override
    public void addToCart(String userId, Integer bookId, Integer quantity) {
        Cart cart = cartRepository.findByUserIdAndBookId(userId, bookId)
                .map(updateQuantity(quantity))
                .orElseGet(() -> createNewCart(userId, bookId, quantity));
        cartRepository.save(cart);
        logger.info("bookId: {} added to cart for user: {}", bookId, userId);
    }

    @Override
    public void removeFromCart(String userId, Integer bookId) {
        cartRepository.deleteByUserIdAndBookId(userId, bookId);
        logger.info("bookId: {} deleted from cart for user: {}", bookId, userId);
    }

    @Override
    public void removeAllFromCart(String userId) {
        cartRepository.deleteByUserId(userId);
        logger.info("deleted all books from cart for user: {}", userId);
    }

    @Override
    public void updateCart(String userId, Integer bookId, Integer quantity) {
        Cart cart = cartRepository.findByUserIdAndBookId(userId, bookId)
                .orElseThrow(() -> new CartItemNotFoundException("Book not found in cart"));
        cart.setQuantity(quantity);
        cartRepository.save(cart);
        logger.info("bookId: {} updated quantity to {} for user: {}", bookId, quantity, userId);
    }

    private CartRequestDTO constructCartRequestDTO(Cart cart) {
        CartRequestDTO cartRequestDTO = new CartRequestDTO();
        cartRequestDTO.setBookId(cart.getBookId());
        cartRequestDTO.setQuantity(cart.getQuantity());
        cartRequestDTO.setUserId(cart.getUserId());
        cartRequestDTO.setTitle(bookRepository.findTitleByBookId(cart.getBookId()).getTitle());
        return cartRequestDTO;
    }

    private static Function<Cart, Cart> updateQuantity(Integer quantity) {
        return cart -> {
            cart.setQuantity(cart.getQuantity() + quantity);
            return cart;
        };
    }

    private Cart createNewCart(String userId, Integer bookId, Integer quantity) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setBookId(bookId);
        cart.setQuantity(quantity);
        return cart;
    }

}
