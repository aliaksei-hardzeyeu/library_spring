package com.example.demo.resources.resources_impl;

import com.example.demo.dto.request.RequestUserDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.dto.response.ResponseUserDto;
import com.example.demo.resources.UserResource;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "User", description = "The User API")
public class UserResourceImpl implements UserResource {
    private final UserService userService;

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Нашли список всех пользователей",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResourceImpl.class)))
                    })
    })
    @GetMapping("/users")
    public List<ResponseUserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь помещен в БД/обновлён",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResourceImpl.class)))
                    })
    })
    @PostMapping("/users")
    public ResponseUserDto upsertUser(@RequestBody RequestUserDto requestUserDto) {
        log.info("UserResourceImpl.upsertUser -> RequestUserDto (" + requestUserDto.getUserId() + " | " + requestUserDto.getUserName() + " )");
        return userService.upsertUser(requestUserDto);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь найден по userId",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResourceImpl.class)))
                    })
    })
    @GetMapping("/users/{userId}")
    public ResponseUserDto getUserById(@PathVariable String userId) {
        log.info("UserResourceImpl.getUserById -> String userId " + userId);
        return userService.getUserById(userId);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список книг пользователя найден по userId",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResourceImpl.class)))
                    })
    })
    @GetMapping("/users/{userId}/books")
    public List<ResponseBookDto> getUsersBookList(@PathVariable String userId) {
        return userService.getUsersBookList(userId);
    }
}
