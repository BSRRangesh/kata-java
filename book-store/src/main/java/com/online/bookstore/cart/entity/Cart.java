package com.online.bookstore.cart.entity;

import com.online.bookstore.book.entity.Book;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @Column(name = "CART_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "BOOK_ID")
    private Integer bookId;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
