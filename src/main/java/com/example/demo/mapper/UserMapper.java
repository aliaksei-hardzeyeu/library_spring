package com.example.demo.mapper;


import com.example.demo.domain.User;
import com.example.demo.dto.request.RequestUserDto;
import com.example.demo.dto.response.ResponseUserDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper
public interface UserMapper {

    ResponseUserDto toResponseUserDto(User user);

    User toUserFromRequestUserDto(RequestUserDto requestUserDto);

    default String map(UUID value) {
        return value.toString();
    }

    default UUID map(String value) {
        if (value == null) {
            return null;
        } else {
            return UUID.fromString(value);
        }
    }
}
