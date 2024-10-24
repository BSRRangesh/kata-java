package com.online.bookstore.cart.service;

import com.online.bookstore.cart.dto.CartRequestDTO;

import java.util.List;

public interface CartService {

    public List<CartRequestDTO> getCart(String userId);
    public void addToCart(String userId, Integer bookId, Integer quantity);
    public void removeFromCart(String userId, Integer bookId);
    public void removeAllFromCart(String userId);

    void updateCart(String userId, Integer bookId, Integer quantity);
}
