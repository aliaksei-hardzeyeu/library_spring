package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users", schema = "computer_science")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id", unique = true, nullable = false)
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    @Email
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "role")
    private String role;
}
