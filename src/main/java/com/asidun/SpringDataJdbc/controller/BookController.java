package com.asidun.SpringDataJdbc.controller;

import com.asidun.SpringDataJdbc.entity.Book;
import com.asidun.SpringDataJdbc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book findBook(@PathVariable Long bookId) {
        return bookService.findBookById(bookId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long bookId) {
        return bookService.updateBook(book, bookId);
    }

//    @PatchMapping("/{bookId}")
//    public Book updateBook(
//            @RequestBody Map<String, String> updates,
//            @PathVariable Long bookId) {
//        return bookService.updateBook(updates, bookId);
//    }
}
