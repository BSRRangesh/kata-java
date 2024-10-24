package com.online.bookstore.cart.dto;

import jakarta.validation.constraints.NotNull;

public class CartRequestDTO {


    @NotNull(message = "Book Id is a mandatory field")
    private Integer bookId;

    private String title;

    @NotNull(message = "Quantity is a mandatory field")
    private Integer quantity;

    @NotNull(message = "User Id is a mandatory field")
    private String userId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
