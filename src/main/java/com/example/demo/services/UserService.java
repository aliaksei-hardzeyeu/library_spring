package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.dto.request.RequestUserDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.dto.response.ResponseUserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.CommonUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserMapper userMapper;
    private final CommonUserRepository commonUserRepository;

    public List<ResponseUserDto> getAllUsers() {
        return commonUserRepository.findAll().stream().
                map(user -> userMapper.toResponseUserDto(user))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseUserDto upsertUser(RequestUserDto requestUserDto) {
        log.info("UserService.upsertUser -> RequestUserDto (" + requestUserDto.getUserId() + " | " + requestUserDto.getUserName() + " )");
        if (requestUserDto.getUserId() == null) {
            User user = userMapper.toUserFromRequestUserDto(requestUserDto);
            log.info("UserService.upsertUser -> User (" + user.getUserId() + " | " + user.getUserName() + " )");
            return userMapper.toResponseUserDto(
                    commonUserRepository.save(user));
        } else {
            Optional<User> user = commonUserRepository.
                    findById(userMapper.toUserFromRequestUserDto(requestUserDto).getUserId());//todo потом добавить эксепшены для неправильного ид
            User currentUser = user.get(); //todo разобраться с ifPresent
            log.info("UserService.upsertUser -> User (" + currentUser.getUserId() + " | " + currentUser.getUserName() + " )");
            currentUser.setUserName(requestUserDto.getUserName());

            return userMapper.toResponseUserDto(commonUserRepository.save(currentUser));
        }
    }

    public ResponseUserDto getUserById(String userId) {
        log.info("UserService.getUserById -> String userId " + userId);
        Optional<User> user = commonUserRepository.findById(UUID.fromString(userId));
        return userMapper.toResponseUserDto(user.get());//todo разобраться с ifPresent
    }

    public List<ResponseBookDto> getUsersBookList(String userId) {
        log.info("UserService.getUsersBookList -> String userId " + userId);
        //todo сначала механизм добавления книг, потом добавления borrow, потом это, тк всё работает через borrow
        return null;
    }

}
