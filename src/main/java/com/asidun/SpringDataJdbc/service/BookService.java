package com.asidun.SpringDataJdbc.service;

import com.asidun.SpringDataJdbc.entity.Book;
import com.asidun.SpringDataJdbc.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public Book updateBook(Book book, Long bookId) {
        book.setId(bookId);
        return bookRepository.save(book);
    }
}
