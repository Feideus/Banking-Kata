package com.example.demo.banking.service;

import com.example.demo.dto.ouput.AccountOutputDto;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import com.example.demo.exception.BankingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.dto.input.OperationInputDto;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)

public class AccountServiceTest {
    @TestConfiguration
    static class AccountServiceTestContextConfiguration {
        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }

    @Autowired
    private AccountService accountService;

    @Test
    public void getAccountDetailShouldSucceed(){
        // Given
        // Account exists and is valid
        OperationInputDto operationInputDto = OperationInputDto
                .builder()
                .amount(10.0)
                .build();

        //When
        AccountOutputDto account = accountService.getAccountDetailById(0L);

        //Then
        assertThat(Objects.nonNull(account.getAccountNumber())).isTrue();
    }

    @Test
    public void getAccountDetailShouldFail(){
        // Given
        // Account doesnt exist and isn't valid
        OperationInputDto operationInputDto = OperationInputDto
                .builder()
                .amount(-10.0)
                .build();

        //When + Then
        assertThrows(BankingException.class, () -> accountService.getAccountDetailById(0L));
    }
}
