package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.input.OperationInputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.exception.BankingException;
import com.example.demo.exception.BankingExceptionMessages;
import com.example.demo.model.Account;
import com.example.demo.model.Operation;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.OperationRepository;

import lombok.AllArgsConstructor;

@Service
// rollback on persistence if service fails or throws error
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OperationService {

	private AccountRepository accountRepository;
	private OperationRepository operationRepository;


	// It would be possible to factorise deposit and withdrawal as they share a lot of code,
	// but I prefer to leave them separated to follow the single responsibility principle

	public OperationOutputDto depositForAccount(final OperationInputDto operationInputDto) throws BankingException {
		checkDepositFunctionalValidity(operationInputDto);

		// waiting for persitence to get added
		Optional<Account> optAccount = accountRepository.findByAccountNumber(
				operationInputDto.getTargetAccountNumber());
		if (optAccount.isEmpty())
			throw new BankingException(BankingExceptionMessages.ACCOUNT_NOT_FOUND);
		optAccount.ifPresent(account -> {
			account.setCurrentBalance(account.getCurrentBalance() + operationInputDto.getAmount());
			accountRepository.save(account);
			// operation is finalized, save operation for history
			Operation operationHistory = Operation
					.builder()
					// delegate id generation to crud
					.sourceAccountNumber(operationInputDto.getSourceAccountNumber())
					.description(operationInputDto.getDescription())
					.amount(operationInputDto.getAmount())
					.targetAccountNumber(operationInputDto.getTargetAccountNumber())
					.operationDate(LocalDateTime.now())
					.build();

			operationRepository.save(operationHistory);
		});

		// return the operation performed
		return OperationOutputDto
				.builder()
				.operationDate(LocalDateTime.now())
				.sourceAccountNumber(operationInputDto.getSourceAccountNumber())
				.targetAccountNumber(operationInputDto.getTargetAccountNumber())
				.build();
	}

	public OperationOutputDto withdrawFromAccount(final OperationInputDto operationInputDto) throws BankingException {
		checkWithdrawFunctionalValidity(operationInputDto);

		// waiting for persitence to get added
		Optional<Account> optAccount = accountRepository.findByAccountNumber(
				operationInputDto.getTargetAccountNumber());

		optAccount.ifPresent(account -> {
			if (!account.getClientId().equals(operationInputDto.getClientId()))
				throw new RuntimeException(BankingExceptionMessages.NOT_AUTHORIZED);
			account.setCurrentBalance(account.getCurrentBalance() - operationInputDto.getAmount());
			accountRepository.save(account);
			// operation is finalized, save operation for history
			Operation operationHistory = Operation
					.builder()
					// delegate id generation to crud
					.sourceAccountNumber(operationInputDto.getSourceAccountNumber())
					.amount(operationInputDto.getAmount())
					.description(operationInputDto.getDescription())
					.targetAccountNumber(operationInputDto.getTargetAccountNumber())
					.operationDate(LocalDateTime.now())
					.build();

			operationRepository.save(operationHistory);
		});

		// return the operation performed
		return OperationOutputDto
				.builder()
				.operationDate(LocalDateTime.now())
				.sourceAccountNumber(operationInputDto.getSourceAccountNumber())
				.targetAccountNumber(operationInputDto.getTargetAccountNumber())
				.build();
	}


	private void checkWithdrawFunctionalValidity(final OperationInputDto operationInputDto) throws
			BankingException {
		if (operationInputDto.getAmount() == null)
			throw new BankingException(BankingExceptionMessages.AMOUNT_IS_NULL);

		if (operationInputDto.getAmount() <= 0)
			throw new BankingException(BankingExceptionMessages.AMOUNT_IS_NEGATIVE);

		if (operationInputDto.getTargetAccountNumber() == null)
			throw new BankingException(BankingExceptionMessages.TARGET_ACCOUNT_NULL);
	}

	/**
	 * verify the operation is valid. throw custom exception if it is not
	 *
	 * @param operationInputDto
	 */
	private void checkDepositFunctionalValidity(final OperationInputDto operationInputDto) throws BankingException {
		if (operationInputDto.getAmount() == null)
			throw new BankingException(BankingExceptionMessages.AMOUNT_IS_NULL);

		if (operationInputDto.getAmount() <= 0)
			throw new BankingException(BankingExceptionMessages.AMOUNT_IS_NEGATIVE);

		if (operationInputDto.getTargetAccountNumber() == null)
			throw new BankingException(BankingExceptionMessages.TARGET_ACCOUNT_NULL);

		if (operationInputDto.getSourceAccountNumber() == null)
			throw new BankingException(BankingExceptionMessages.SOURCE_ACCOUNT_NULL);
	}
}