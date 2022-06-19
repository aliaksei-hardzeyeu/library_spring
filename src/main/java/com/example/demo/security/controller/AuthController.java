package com.example.demo.security.controller;

import com.example.demo.dto.response.ResponseUserDto;
import com.example.demo.security.dto.RequestLoginDto;
import com.example.demo.security.dto.RequestNewUserDto;
import com.example.demo.security.dto.ResponseLoginDto;
import com.example.demo.security.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Аутентификация и авторизация",
            description = "token необходим для каждого запроса, кроме случаев авторизации/аутентификации и случаев, когда срок жизни обычного токена истёк.\n" +
                    "Формат токена — Bearer. \n" +
                    "freshToken необходим для получения новой пары токенов, т.к. срок жизни обычного токена (в целях безопасности) ограничен")
    @PostMapping("/signin")
    public ResponseLoginDto authenticateUser(@Valid @RequestBody RequestLoginDto loginRequest) {
        return authService.login(loginRequest);
    }

    @Operation(summary = "Получить новую пару токенов с помощью refreshToken")
    @PostMapping("/refresh")
    public ResponseLoginDto refreshToken(@RequestHeader(AUTHORIZATION) String refreshToken) {
        return authService.refreshJwt(refreshToken);
    }

    @Operation(summary = "Создать нового юзера")
    @PostMapping("/create")
    public ResponseLoginDto createNewUser(@RequestBody RequestNewUserDto requestNewUserDto) {
        return authService.createNewUser(requestNewUserDto);
    }

}