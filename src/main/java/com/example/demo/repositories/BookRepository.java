package com.example.demo.repositories;

import com.example.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query(value = "select b from Book b where b.author.authorId = :authorId")
    List<Book> findAllByAuthorId(@Param("authorId") UUID authorId);
}
