package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "books", schema = "computer_science")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id", unique = true, nullable = false)
    private UUID bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_release_date")
    private Date bookReleaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_author_id")
    private Author author;

    public Book(String bookName, Date bookReleaseDate, Author author) {
        this.bookId = UUID.randomUUID();
        this.bookName = bookName;
        this.bookReleaseDate = bookReleaseDate;
        this.author = author;
    }
}
