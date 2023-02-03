package com.example.demo.dto.abstractDto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class AbstractOperationDto implements Serializable {
	private Long id;
	private Long clientId;
	private String sourceAccountNumber;
	private String targetAccountNumber;

	public AbstractOperationDto() {
	}

	// add operation status SUCCESS/FAILURE in future interations
}
