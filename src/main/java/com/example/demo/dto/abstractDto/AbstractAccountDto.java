package com.example.demo.dto.abstractDto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class AbstractAccountDto {
	private Long accountId;
	private String accountNumber;
	private Double currentBalance;
	private Long clientId;
}
