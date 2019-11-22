package com.asidun.SpringDataJdbc;

import com.asidun.SpringDataJdbc.entity.Book;
import com.asidun.SpringDataJdbc.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureDataJdbc
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    void TestUpdateBook() {
        Book book = Book.builder().title("HomeAlone").author("Author").build();
        bookService.createBook(book);
        book.setAuthor("Author111");
        Book result = bookService.updateBook(book, book.getId());
        assertThat(book.getAuthor()).isEqualTo("Author111");
    }
}
