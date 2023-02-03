package com.example.demo.dto.abstractDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public abstract class AbstractAccountDto{
	private Long accountId;
	private String accountNumber;
	private Double currentBalance;
	private Long clientId;

	public AbstractAccountDto() {
	}
}
