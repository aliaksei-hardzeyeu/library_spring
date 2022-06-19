package com.example.demo.resources.resources_impl;

import com.example.demo.dto.request.RequestBookDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.resources.BookResource;
import com.example.demo.services.BookService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Book", description = "The Book API")
public class BookResourceImpl implements BookResource {
    private final BookService bookService;

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Книга помещена в БД/обновлена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BookResourceImpl.class)))
                    })
    })
    @PostMapping("/books")
    public ResponseBookDto upsertBook(@RequestBody RequestBookDto requestBookDto) {
        return bookService.upsertBook(requestBookDto);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получен список книг",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BookResourceImpl.class)))
                    })
    })
    @GetMapping("/books")
    public List<ResponseBookDto> getBookList() {
        return bookService.getBookList();
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получена книга по bookId",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BookResourceImpl.class)))
                    })
    })
    @GetMapping("/books/{bookId}")
    public ResponseBookDto getBookById(@PathVariable String bookId) {
        return bookService.getBookById(bookId);
    }
}
