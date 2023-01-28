package com.example.demo.dto.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountInputDto {
    private Long accountId;
    private String accountNumber;
    private Double currentBalance;
    // Create Client class in next iteration
    private Long clientId;
}
