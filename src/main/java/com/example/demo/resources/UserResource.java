package com.example.demo.resources;

import com.example.demo.dto.request.RequestUserDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.dto.response.ResponseUserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping(value = "/api/")
public interface UserResource {
    //todo будущий сервис админов для управления юзерами в плане удаления добавления

    @Operation(description = "Получить список всех юзеров", summary = "Получить список всех юзеров")
    List<ResponseUserDto> getAllUsers();

    @Operation(description = "Поместить пользователя в БД", summary = "Поместить пользователя в БД")
    ResponseUserDto upsertUser(RequestUserDto requestUserDto);

    @Operation(description = "Найти пользователя по userId", summary = "Найти пользователя по userId")
    ResponseUserDto getUserById(String userId);

    @Operation(description = "Найти список книг пользователя по userId", summary = "Найти список книг пользователя по userId")
    List<ResponseBookDto> getUsersBookList(String userId);
}
