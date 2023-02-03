package com.example.demo.banking.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.input.AccountInputDto;
import com.example.demo.dto.ouput.AccountOutputDto;
import com.example.demo.exception.BankingException;
import com.example.demo.model.Account;
import com.example.demo.model.Operation;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.implementationLayer.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private OperationRepository operationRepository;

    @Test
    public void getAccountDetailShouldSucceed() throws BankingException {
        // Given
        // Account exists and is valid
        AccountInputDto accountInputDto = AccountInputDto
                .builder()
                .accountNumber("0000001")
                .clientId(1010L)
                .build();

        Optional<Account> optAccount = Optional.of(Account
                .builder()
                .accountId(1000L)
                .clientId(1010L)
                .accountNumber("00000001")
                .currentBalance(1000.00)
                .build());

        //When
        when(accountRepository.findByAccountNumber("0000001")).thenReturn(optAccount);
        AccountOutputDto account = accountService.getAccountDetailById(accountInputDto);

        //Then
        assertTrue(Objects.nonNull(account.getCurrentBalance()));
    }

    @Test
    public void getAccountDetailShouldFail(){
        // Given
        // Account doesnt exist and isn't valid
        AccountInputDto accountInputDto = AccountInputDto
                .builder()
                .accountNumber("-09999999")
                .clientId(1010L)
                .build();

        Optional<Account> optAccount = Optional.of(Account
                .builder()
                .accountId(1000L)
                .clientId(1010L)
                .accountNumber("00000001")
                .currentBalance(1000.00)
                .build());

        //When + Then
        when(accountRepository.findByAccountNumber("0000001")).thenReturn(optAccount);
        assertThrows(BankingException.class, () -> accountService.getAccountDetailById(accountInputDto));
    }

    @Test
    public void getAccountDetailUnauthorisedShouldFail(){
        // Given
        // Account doesnt exist and isn't valid
        AccountInputDto accountInputDto = AccountInputDto
                .builder()
                .accountNumber("-09999999")
                // User does not own the account
                .clientId(20000L)
                .build();

        Optional<Account> optAccount = Optional.of(Account
                .builder()
                .accountId(1000L)
                .clientId(1010L)
                .accountNumber("00000001")
                .currentBalance(1000.00)
                .build());

        //When + Then
        when(accountRepository.findByAccountNumber("0000001")).thenReturn(optAccount);
        assertThrows(BankingException.class, () -> accountService.getAccountDetailById(accountInputDto));
    }

    @Test
    public void getOperationHistoryShouldSucceed() throws BankingException {
        // Given
        // Account doesnt exist and isn't valid
        AccountInputDto accountInputDto = AccountInputDto
                .builder()
                .accountNumber("0000001")
                .clientId(20000L)
                .build();

        List<Operation> operationHistory = new ArrayList<>();
        Operation op1 = Operation
                .builder()
                .amount(10.00)
                .description("dummy description")
                .sourceAccountNumber("0000001")
                .targetAccountNumber("0000002")
                .build();
        Operation op2 = Operation
                .builder()
                .amount(25.00)
                .description("dummy description")
                .sourceAccountNumber("0000002")
                .targetAccountNumber("0000001")
                .build();

        operationHistory.add(op1);
        operationHistory.add(op2);

        //When + Then
        when(operationRepository.findBysourceAccountNumberOrderByOperationDate("0000001")).thenReturn(operationHistory);
        assertTrue(accountService.getOperationsForAccount(accountInputDto).size() > 0);
    }
}
