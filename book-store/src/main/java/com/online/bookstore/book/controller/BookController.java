package com.online.bookstore.book.controller;

import com.online.bookstore.book.entity.Book;
import com.online.bookstore.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }

}
