package com.online.bookstore.book.repository;

import com.online.bookstore.book.dto.BookTitle;
import com.online.bookstore.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    BookTitle findTitleByBookId(Integer bookId);
}
