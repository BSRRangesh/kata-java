package com.online.bookstore.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderDetailDTO {

    @NotNull(message = "Book Id is a mandatory field")
    private Integer bookId;

    @NotNull(message = "Quantity is a mandatory field")
    @Min(value = 1, message = "Minimum quantity is 1")
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}