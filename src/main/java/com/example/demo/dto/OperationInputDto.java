package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OperationInputDto {
    private Long id;

    private Long sourceAccountId;

    private Long targetAccountId;

    private Double amount;

    private LocalDateTime operationDate;
}
