package com.example.demo.validation;


import com.example.demo.domain.User;
import com.example.demo.repositories.CommonUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserValidator {
    private final CommonUserRepository commonUserRepository;


    public boolean isUserNameUnique(String userName) {
        log.info("UserValidator -> isUserNameUnique: userName = {}", userName);
        if (Optional.ofNullable(commonUserRepository.findUserByUserName(userName)).isEmpty()) {
            return true;
        } else {
            throw new RuntimeException("isUserNameUnique -> FALSE");
        }
    }

    public boolean isUserEmailUnique(String userEmail) {
        log.info("UserValidator -> isUserEmailUnique: userEmail = {}", userEmail);
        if (Optional.ofNullable(commonUserRepository.findUserByUserEmail(userEmail)).isEmpty()) {
            return true;
        } else {
            throw new RuntimeException("isUserEmailUnique -> FALSE");
        }
    }

    public boolean isUserRolePresent(String userRole) {
        log.info("UserValidator -> isUserRolePresent: userRole = {}", userRole);
        if (userRole != null) {
            return true;
        } else {
            throw new RuntimeException("isUserRolePresent -> FALSE");
        }
    }
}
