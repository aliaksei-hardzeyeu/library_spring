package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBookDto {
    private String bookId;
    private String bookName;
    private String bookReleaseDate;
    private ResponseAuthorDto responseAuthorDto;

}
