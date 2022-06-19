package com.example.demo.security.dto;

import lombok.Data;

@Data
public class ResponseLoginDto {
    private String jwtToken;
    private String jwtRefreshToken;
}
