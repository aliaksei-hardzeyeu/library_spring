package com.example.demo.resources;

import com.example.demo.dto.request.RequestBookDto;
import com.example.demo.dto.response.ResponseBookDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/api/")
public interface BookResource {

    @Operation(summary = "Вставить/изменить книгу", description = "Вставить новую или изменить старую книгу")
    ResponseBookDto upsertBook(RequestBookDto requestBookDto);

    @Operation(summary = "Получить список всех книг", description = "Получить список всех книг")
    List<ResponseBookDto> getBookList();

    @Operation(summary = "Получить книгу по bookId", description = "Получить книгу по bookId")
    ResponseBookDto getBookById(String bookId);

}
