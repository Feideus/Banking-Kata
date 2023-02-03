package com.example.demo.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.demo.dto.abstractDto.AbstractOperationDto;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class OperationInputDto extends AbstractOperationDto implements Serializable {
    private Double amount;
    private String description;


    // Add custom attributes if necessary

    public OperationInputDto() {
    }


}
