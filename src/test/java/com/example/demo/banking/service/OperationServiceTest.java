package com.example.demo.banking.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.input.OperationInputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.exception.BankingException;
import com.example.demo.model.Account;
import com.example.demo.model.Operation;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.implementationLayer.OperationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceTest {

	@Autowired
	private OperationService operationService;

	@MockBean
	private OperationRepository operationRepository;
	@MockBean
	private AccountRepository accountRepository;


	@Test
	public void operationValidDepotShouldSucceed() throws BankingException {
		// Given
		// OperationInput is valid
		OperationInputDto operationInputDto = OperationInputDto
				.builder()
				.sourceAccountNumber("0000003")
				.targetAccountNumber("0010002")
				.amount(10.0)
				.build();

		Optional<Account> optAccountTarget = Optional.of(Account
				.builder()
				.accountId(1000L)
				.clientId(1010L)
				.accountNumber("00000001")
				.currentBalance(1000.00)
				.build());

		Optional<Account> optAccountSource = Optional.of(Account
				.builder()
				.accountId(1000L)
				.clientId(1010L)
				.accountNumber("00000001")
				.currentBalance(1000.00)
				.build());

		//When
		when(accountRepository.findByAccountNumber("0010002")).thenReturn(optAccountTarget);
		when(accountRepository.findByAccountNumber("0000003")).thenReturn(optAccountSource);

		when(operationRepository.save(Mockito.any(Operation.class))).thenReturn(null);
		OperationOutputDto output = operationService.depositForAccount(operationInputDto);

		//Then
		assertTrue(output.getTargetAccountNumber() != null && output.getSourceAccountNumber() != null);
	}

	@Test
	public void operationInvalidDepotShouldFail() {
		//Given
		// OperationInput is invalid, amount is negative
		OperationInputDto operationInputDto = OperationInputDto
				.builder()
				.sourceAccountNumber("0000002")
				.targetAccountNumber("0000001")
				.amount(-10.0)
				.build();

		//When
		assertThrows(BankingException.class, () -> {
			operationService.depositForAccount(operationInputDto);
		});
	}

	@Test
	public void operationValidWithdrawShouldSucceed() throws BankingException {
		// Given
		// OperationInput is valid
		OperationInputDto operationInputDto = OperationInputDto
				.builder()
				.targetAccountNumber("0000001")
				.amount(10.0)
				.build();

		//When
		OperationOutputDto output = operationService.withdrawFromAccount(operationInputDto);

		//Then
		assertNotNull(output.getTargetAccountNumber());
	}

	@Test
	public void operationInvalidWithdrawShouldFail() {
		//Given
		// OperationInput is invalid, amount is negative
		OperationInputDto operationInputDto = OperationInputDto
				.builder()
				.targetAccountNumber("0000001")
				.amount(-10.0)
				.build();

		//When
		assertThrows(BankingException.class, () -> {
			operationService.withdrawFromAccount(operationInputDto);
		});
	}
}
