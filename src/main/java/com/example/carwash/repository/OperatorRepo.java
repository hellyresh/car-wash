package com.example.carwash.repository;

import com.example.carwash.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepo extends JpaRepository<Operator, Long> {
    Optional<Object> findByUserId(Long id);

    boolean existsByBoxId(Long boxId);
}
