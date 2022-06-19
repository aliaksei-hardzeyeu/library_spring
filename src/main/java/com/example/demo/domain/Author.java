package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "authors", schema = "computer_science")
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "author_id", unique = true, nullable = false)
    private UUID authorId;

    @Column(name = "author_first_name")
    private String authorFirstName;

    @Column(name = "author_second_name")
    private String authorSecondName;


    public Author(String authorFirstName, String authorSecondName) {
        this.authorId = UUID.randomUUID();
        this.authorFirstName = authorFirstName;
        this.authorSecondName = authorSecondName;
    }
}
