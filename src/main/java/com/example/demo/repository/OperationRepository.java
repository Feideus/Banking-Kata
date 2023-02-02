package com.example.demo.repository;

import com.example.demo.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findBySourceAccountIdOrderByOperationDate(Long id);
}
