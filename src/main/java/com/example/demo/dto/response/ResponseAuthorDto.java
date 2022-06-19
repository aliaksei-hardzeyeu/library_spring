package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAuthorDto {
    private String authorId;

    private String authorFirstName;

    private String authorSecondName;
}
