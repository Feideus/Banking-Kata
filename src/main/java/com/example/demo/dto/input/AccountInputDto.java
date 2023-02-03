package com.example.demo.dto.input;

import java.io.Serializable;

import com.example.demo.dto.abstractDto.AbstractAccountDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class AccountInputDto extends AbstractAccountDto implements Serializable {
    // Add custom attributes if necessary

	public AccountInputDto() {

	}
}
