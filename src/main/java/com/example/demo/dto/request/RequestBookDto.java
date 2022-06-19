package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBookDto {
    private String bookId;
    private String bookName;

    @Pattern(regexp = "^(?:[0-9][0-9])?[0-9][0-9]-[0-3][0-9]-[0-3][0-9]$")
    private String bookReleaseDate;

    private RequestAuthorDto requestAuthorDto;
}
