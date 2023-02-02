package com.example.demo.dto.abstractDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class AbstractOperationDto {
	private Long id;
	private Long clientId;
	private String sourceAccountNumber;
	private String targetAccountNumber;

	// add operation status SUCCESS/FAILURE in future interations
}
