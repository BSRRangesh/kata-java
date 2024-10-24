package com.online.bookstore.book.entity;


import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "PUBLISHED_DATE")
    private Date publishedDate;

    @Column(name = "DESCRIPTION")
    private String description;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

