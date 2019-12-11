package com.asidun.SpringDataJdbc.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
}
