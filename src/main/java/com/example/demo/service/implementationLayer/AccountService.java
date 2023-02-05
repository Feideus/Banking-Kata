package com.example.demo.service.implementationLayer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.input.AccountInputDto;
import com.example.demo.dto.ouput.AccountOutputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.exception.BankingException;
import com.example.demo.exception.BankingExceptionMessages;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.interfaceLayer.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
// rollback on persistence if service fails or throws error
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService implements IAccountService {

	private AccountRepository accountRepository;

	private OperationRepository operationRepository;

	public AccountOutputDto getAccountDetailById(final AccountInputDto accountInputDto) throws BankingException {
		verifyAccountInputValidity(accountInputDto);

		Optional<Account> optAccount = accountRepository.findByAccountNumber(accountInputDto.getAccountNumber());

		if (optAccount.isEmpty())
			throw new BankingException(BankingExceptionMessages.ACCOUNT_NOT_FOUND,400);
		else {
			Account account = optAccount.get();
			// if user asks for a someone else's account detail, reject
			if (!account.getClientId().equals(accountInputDto.getClientId()))
				throw new BankingException(BankingExceptionMessages.NOT_AUTHORIZED,401);
			return AccountOutputDto
					.builder()
					.currentBalance(account.getCurrentBalance())
					.accountNumber(account.getAccountNumber())
					.build();
		}
	}

	public List<OperationOutputDto> getOperationsForAccount(final AccountInputDto accountInputDto) throws BankingException {
		verifyAccountInputValidity(accountInputDto);

		return operationRepository.findBysourceAccountNumberOrderByOperationDate(accountInputDto.getAccountNumber())
				.stream().map(operation -> OperationOutputDto
						.builder()
						.operationDate(operation.getOperationDate())
						.targetAccountNumber(operation.getTargetAccountNumber())
						.sourceAccountNumber(operation.getSourceAccountNumber())
						.amount(operation.getAmount())
						.description(operation.getDescription())
						.build()).collect(
				Collectors.toList());
	}


	private void verifyAccountInputValidity(final AccountInputDto accountInputDto) throws BankingException {
		if (accountInputDto.getAccountNumber() == null)
			throw new BankingException(BankingExceptionMessages.TARGET_ACCOUNT_NULL,400);
		if (accountInputDto.getClientId() == null)
			throw new BankingException(BankingExceptionMessages.NOT_AUTHORIZED,401);
	}
}
