package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.input.AccountInputDto;
import com.example.demo.dto.ouput.AccountOutputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.exception.BankingException;
import com.example.demo.service.implementationLayer.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountOutputDto> getAccountDetail(@RequestBody AccountInputDto accountInputDto) {
		try {
			return new ResponseEntity<>(accountService.getAccountDetailById(accountInputDto), HttpStatus.OK);
		} catch (BankingException bankingException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	@PostMapping(value = "/{accountNumber}/operation",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OperationOutputDto>> getAccountOperations(@RequestBody AccountInputDto accountInputDto) {
		try {
			return new ResponseEntity<>(accountService.getOperationsForAccount(accountInputDto), HttpStatus.OK);
		} catch (BankingException bankingException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
