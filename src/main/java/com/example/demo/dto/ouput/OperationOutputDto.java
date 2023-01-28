package com.example.demo.dto.ouput;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OperationOutputDto {
    private Long sourceAccountId;
    private Long targetAccountId;
    private Double amount;
    private LocalDateTime operationDate;
}
