package com.example.demo.dto.ouput;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountOutputDto {
    private String accountNumber;
    private Double currentBalance;
    private Long clientId;
}
