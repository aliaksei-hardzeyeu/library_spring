package com.example.demo.repositories;

import com.example.demo.domain.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BorrowRepository extends JpaRepository<Borrow, UUID> {
}
