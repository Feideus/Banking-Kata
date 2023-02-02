package com.example.demo.dto.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.time.LocalDateTime;

import com.example.demo.dto.abstractDto.AbstractOperationDto;

@Getter
@Setter
@SuperBuilder
public class OperationInputDto extends AbstractOperationDto {
    private Double amount;
    private String description;

    // Add custom attributes if necessary

}
