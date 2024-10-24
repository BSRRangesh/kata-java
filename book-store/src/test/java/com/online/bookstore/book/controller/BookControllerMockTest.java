package com.online.bookstore.book.controller;

import com.online.bookstore.book.entity.Book;
import com.online.bookstore.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerMockTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetBooksNoEntries() {

        when(bookRepository.findAll()).thenReturn(null);
        ResponseEntity<List<Book>> response = bookController.getBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetBooksWithEntries() {
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");
        book1.setPrice(100.0);
        book1.setDescription("Description 1");

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setAuthor("Author 1");
        book2.setPrice(1000.0);
        book2.setDescription("Description 2");

        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        ResponseEntity<List<Book>> response = bookController.getBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
    }
}
