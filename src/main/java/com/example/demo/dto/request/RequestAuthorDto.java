package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAuthorDto {
    private String authorId;

    private String authorFirstName;

    private String authorSecondName;
}
