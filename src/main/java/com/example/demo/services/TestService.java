package com.example.demo.services;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TestService {

//    private final AuthorRepository authorRepository;
//    private final BookRepository bookRepository;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void run() {
//        Author a1 = new Author();
//
//        Book b1 = new Book("book1", Date.valueOf("2020-07-11"), a1);
//        Book b2 = new Book("book2", Date.valueOf("2019-07-11"), a1);
//        Book b3 = new Book("book3", Date.valueOf("2018-07-11"), a1);
//        Book b4 = new Book("book4", Date.valueOf("2017-07-11"), a1);
//        Book b5 = new Book("book5", Date.valueOf("2016-07-11"), a1);
//        List<Book> books1 = List.of(b1, b2, b3, b4, b5);
//
//        a1.setAuthorFirstName("first_name_1");
//        a1.setAuthorSecondName("second_name_1");
//
//        authorRepository.saveAndFlush(a1);
//        bookRepository.saveAllAndFlush(books1);
//
//    }
}
