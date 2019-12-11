package com.asidun.SpringDataJdbc.controller;

import com.asidun.SpringDataJdbc.entity.Book;
import com.asidun.SpringDataJdbc.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void initBook() {
        book = Book.builder()
                .author("Author")
                .title("Book")
                .build();
    }

    @Test
    void addNewBook() throws Exception {
        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
        Book resultBook = bookRepository.findByAuthor("Author");
        assertThat(resultBook.getTitle()).isEqualTo("Book");
    }

    @Test
    void updateBook() throws Exception {
        book = bookRepository.save(book);
        book.setAuthor("New Author");
        bookRepository.save(book);
        Book savedBook = book;
        savedBook.setAuthor("REST Author");

        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(savedBook)))
                .andExpect(status().isOk());

        Book resultBook = bookRepository.findByAuthor("REST Author");
        assertThat(resultBook.getTitle()).isEqualTo("Book");
        Book resultOldBook = bookRepository.findByAuthor("Author");
        assertThat(resultOldBook).isNull();
    }

    @Test
    void updateBookById() throws Exception {
        Book savedBook = bookRepository.save(book);
        savedBook.setAuthor("New Author");
        mockMvc.perform(put("/books/{id}", savedBook.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());

        Book resultBook = bookRepository.findByAuthor("New Author");
        assertThat(resultBook.getTitle()).isEqualTo("Book");
        Book resultOldBook = bookRepository.findByAuthor("Author");
        assertThat(resultOldBook).isNull();
    }
}
