package com.online.bookstore.cart.repository;

import com.online.bookstore.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUserId(String userId);

    Optional<Cart> findByUserIdAndBookId(String userId, Integer bookId);

    void deleteByUserIdAndBookId(String userId, Integer bookId);

    void deleteByUserId(String userId);
}
