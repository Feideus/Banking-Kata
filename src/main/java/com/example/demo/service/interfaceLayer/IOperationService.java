package com.example.demo.service.interfaceLayer;

import com.example.demo.dto.input.OperationInputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.exception.BankingException;

public interface IOperationService {

	OperationOutputDto depositForAccount(final OperationInputDto operationInputDto) throws BankingException;

	OperationOutputDto withdrawFromAccount(final OperationInputDto operationInputDto) throws BankingException;
}
