package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {
    private String userId;

    private String userName;

    public RequestUserDto(String userName) {
        this.userName = userName;
    }
}

