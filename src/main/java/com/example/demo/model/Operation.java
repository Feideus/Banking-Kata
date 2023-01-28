package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class Operation {
    private Long operationId;
    private String description;
    private Long sourceAccountId;
    private Long targetAccountId;
    private Double amount;
    private LocalDateTime operationDate;
}
