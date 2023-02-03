package com.example.demo.service.interfaceLayer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.input.AccountInputDto;
import com.example.demo.dto.ouput.AccountOutputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.exception.BankingException;

public interface IAccountService {

	AccountOutputDto getAccountDetailById(final AccountInputDto accountInputDto) throws BankingException;

	List<OperationOutputDto> getOperationsForAccount(final AccountInputDto accountInputDto)
			throws BankingException;
}
