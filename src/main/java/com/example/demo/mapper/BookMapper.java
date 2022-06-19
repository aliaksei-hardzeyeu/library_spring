package com.example.demo.mapper;

import com.example.demo.domain.Book;
import com.example.demo.dto.request.RequestBookDto;
import com.example.demo.dto.response.ResponseBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Date;
import java.util.UUID;

@Mapper
public interface BookMapper {

    @Mapping(target = "bookReleaseDate", source = "bookReleaseDate", qualifiedByName = "stringToDate")
    Book toBookFromRequestBookDto(RequestBookDto requestBookDto);

    @Mapping(target = "bookReleaseDate", source = "bookReleaseDate", qualifiedByName = "dateToString")
    @Mapping(target = "responseAuthorDto", source = "author")
    ResponseBookDto toResponseBookDto(Book book);

    @Named("dateToString")
    static String dateToString(Date date) {
        return date.toString();
    }

    @Named("stringToDate")
    static Date stringToDate(String releaseDate) {
        return Date.valueOf(releaseDate);
    }

    default String map(UUID uuid) {
        return uuid.toString();
    }

    default UUID map(String value) {
        if (value != null) {
            return UUID.fromString(value);
        } else {
            return null;
        }
    }
}
