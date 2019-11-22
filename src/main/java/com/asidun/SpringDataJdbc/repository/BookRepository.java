package com.asidun.SpringDataJdbc.repository;

import com.asidun.SpringDataJdbc.entity.Book;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Long> {
    //Book findByTitle(String title);

    @Query("select * from Book b where b.author = :author")
    Book findByAuthor(@Param("author") String author);
}
