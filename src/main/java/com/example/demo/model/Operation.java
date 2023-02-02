package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@Builder
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;
    private String description;
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private Double amount;
    private LocalDateTime operationDate;
}
