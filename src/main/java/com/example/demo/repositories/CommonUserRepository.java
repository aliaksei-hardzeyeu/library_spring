package com.example.demo.repositories;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommonUserRepository extends JpaRepository<User, UUID> {
    User findUserByUserEmail(String userEmail);

    User findUserByUserName(String userName);
}
