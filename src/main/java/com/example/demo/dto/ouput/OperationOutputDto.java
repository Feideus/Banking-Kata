package com.example.demo.dto.ouput;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import com.example.demo.dto.abstractDto.AbstractOperationDto;

@Getter
@Setter
@SuperBuilder
public class OperationOutputDto extends AbstractOperationDto {

	private LocalDateTime operationDate;

	// Add custom attributes if necessary
}
