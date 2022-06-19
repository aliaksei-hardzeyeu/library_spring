package com.example.demo.resources.resources_impl;

import com.example.demo.dto.request.RequestAuthorDto;
import com.example.demo.dto.response.ResponseAuthorDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.resources.AuthorResource;
import com.example.demo.services.AuthorService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Author", description = "The Author API")
public class AuthorResourceImpl implements AuthorResource {

    private final AuthorService authorService;

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Нашли список всех авторов",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AuthorResourceImpl.class)))
                    })
    })
    @GetMapping("/authors")
    public List<ResponseAuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Поместили/обновили автора в БД",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AuthorResourceImpl.class)))
                    })
    })
    @PostMapping("/authors")
    public ResponseAuthorDto upsertAuthor(RequestAuthorDto requestAuthorDto) {
        return authorService.upsertAuthor(requestAuthorDto);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Нашли автора по authorId",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AuthorResourceImpl.class)))
                    })
    })
    @GetMapping("/authors/{authorId}")
    public ResponseAuthorDto getAuthorById(@PathVariable String authorId) {
        return authorService.getAuthorById(authorId);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Нашли список всех книг данного автора по authorId",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AuthorResourceImpl.class)))
                    })
    })
    @GetMapping("/authors/{authorId}/books")
    public List<ResponseBookDto> getAuthorsBookList(@PathVariable String authorId) {
        return authorService.getAuthorsBookList(authorId);
    }
}
