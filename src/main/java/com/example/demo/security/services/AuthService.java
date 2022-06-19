package com.example.demo.security.services;

import com.example.demo.domain.User;
import com.example.demo.dto.request.RequestUserDto;
import com.example.demo.dto.response.ResponseUserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.CommonUserRepository;
import com.example.demo.security.dto.RequestLoginDto;
import com.example.demo.security.dto.RequestNewUserDto;
import com.example.demo.security.dto.ResponseLoginDto;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final CommonUserRepository commonUserRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;


    public ResponseLoginDto login(RequestLoginDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));


        String jwt = jwtUtils.generateJwtToken(authentication);
        String refreshJwt = jwtUtils.generateRefreshToken(authentication);

        ResponseLoginDto responseLoginDto = new ResponseLoginDto();
        responseLoginDto.setJwtToken(jwt);
        responseLoginDto.setJwtRefreshToken(refreshJwt);

        return responseLoginDto;
    }

    public ResponseLoginDto refreshJwt(String authHeader) {
        String token = jwtUtils.parseJwt(authHeader);
        if (!jwtUtils.validateJwtToken(token)) {
            throw new RuntimeException("Auth failed"); // TODO: custom exception
        }

        String email = jwtUtils.getEmailFromJwtToken(token);

        String accessToken = jwtUtils.generateJwtToken(email);
        String refreshToken = jwtUtils.generateRefreshToken(email);

        ResponseLoginDto responseLoginDto = new ResponseLoginDto();
        responseLoginDto.setJwtToken(accessToken);
        responseLoginDto.setJwtRefreshToken(refreshToken);

        return responseLoginDto;
    }

    /**
     * Создаёт нового пользователя и сразу логинится (подразумевается, что юзер незалогинен)
     * @param requestNewUserDto
     * @return
     */
    public ResponseLoginDto createNewUser(RequestNewUserDto requestNewUserDto) {
        User user = new User();
        user.setUserName(requestNewUserDto.getUserName());
        user.setUserEmail(requestNewUserDto.getUserEmail());
        user.setUserPassword(new BCryptPasswordEncoder().encode(requestNewUserDto.getUserEmail()));
        user.setRole(requestNewUserDto.getUserRole());

        userValidator.isUserEmailUnique(user.getUserEmail());
        userValidator.isUserNameUnique(user.getUserName());
        userValidator.isUserRolePresent(user.getRole());

        userMapper.toResponseUserDto(commonUserRepository.save(user));
        RequestLoginDto requestLoginDto = new RequestLoginDto(user.getUserEmail(), user.getUserPassword());

        return login(requestLoginDto);
    }

}
