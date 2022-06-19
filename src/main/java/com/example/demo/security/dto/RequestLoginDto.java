package com.example.demo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class RequestLoginDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
