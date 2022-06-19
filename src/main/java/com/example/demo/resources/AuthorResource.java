package com.example.demo.resources;

import com.example.demo.dto.request.RequestAuthorDto;
import com.example.demo.dto.request.RequestUserDto;
import com.example.demo.dto.response.ResponseAuthorDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.dto.response.ResponseUserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/api/")
public interface AuthorResource {
    @Operation(description = "Получить список всех авторов", summary = "Получить список всех авторов")
    List<ResponseAuthorDto> getAllAuthors();

    @Operation(description = "Поместить/обновить автора в БД", summary = "Поместить/обновить авторая в БД")
    ResponseAuthorDto upsertAuthor(RequestAuthorDto requestAuthorDto);

    @Operation(description = "Найти автора по authorId", summary = "Найти автора по authorId")
    ResponseAuthorDto getAuthorById(String authorId);

    @Operation(description = "Найти список книг автора по authorId", summary = "Найти список книг автора по authorId")
    List<ResponseBookDto> getAuthorsBookList(String authorId);
}
