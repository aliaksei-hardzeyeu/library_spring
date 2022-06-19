package com.example.demo.security.dto;

import lombok.Data;

@Data
public class RequestNewUserDto {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userRole;
}
