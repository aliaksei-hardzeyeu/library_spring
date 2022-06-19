package com.example.demo.services;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.dto.request.RequestBookDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    //не работает апдейт
    public ResponseBookDto upsertBook(RequestBookDto requestBookDto) {
        log.info("BookService.upsertBook -> bookId {} | bookName {} | bookReleaseDate {} | bookAuthor {} ",
                requestBookDto.getBookId(), requestBookDto.getBookName(), requestBookDto.getBookReleaseDate(), requestBookDto.getRequestAuthorDto());
        if (requestBookDto.getBookId() == null) {
            Book book = bookMapper.toBookFromRequestBookDto(requestBookDto);
            Author author = authorMapper.fromRequestAuthorDtoToAuthor(requestBookDto.getRequestAuthorDto());
            book.setAuthor(author);
            authorRepository.save(author);
            return bookMapper.toResponseBookDto(bookRepository.save(book));
        } else {
            Optional<Book> book = bookRepository.findById(UUID.fromString(requestBookDto.getBookId()));
            Book currentBook = book.get();
            currentBook.setBookName(requestBookDto.getBookName());
            currentBook.setBookReleaseDate(Date.valueOf(requestBookDto.getBookReleaseDate()));
            Author author = authorMapper.fromRequestAuthorDtoToAuthor(requestBookDto.getRequestAuthorDto());
            authorRepository.save(author);
            currentBook.setAuthor(author);

            return bookMapper.toResponseBookDto(bookRepository.save(currentBook));
        }
    }

    public List<ResponseBookDto> getBookList() {
        return bookRepository.findAll().stream()
                .map(book -> bookMapper.toResponseBookDto(book))
                .collect(Collectors.toList());
    }

    public ResponseBookDto getBookById(String bookId) {
        Book book = bookRepository.findById(UUID.fromString(bookId)).get();
        log.info("BookService - getBookById -> book = {}", book.toString());
        return bookMapper.toResponseBookDto(book);
    }
}
