package com.example.demo.mapper;

import com.example.demo.domain.Author;
import com.example.demo.dto.request.RequestAuthorDto;
import com.example.demo.dto.response.ResponseAuthorDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper
public interface AuthorMapper {

    Author fromRequestAuthorDtoToAuthor(RequestAuthorDto requestAuthorDto);
    ResponseAuthorDto fromAuthorToResponseAuthorDto(Author author);

    default String map(UUID uuid) {
        return uuid.toString();
    }

    default UUID map (String value) {
        if (value != null) {
            return UUID.fromString(value);
        } else {
            return null;
        }
    }
}
