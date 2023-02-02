package com.example.demo.dto.input;

import com.example.demo.dto.abstractDto.AbstractAccountDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

@Getter
@Setter
@SuperBuilder
public class AccountInputDto extends AbstractAccountDto {
    // Add custom attributes if necessary

}
