package com.example.demo.repository;

import com.example.demo.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findBySourceAccountIdOrderByOperationDate(Long id);
}
