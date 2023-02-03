package com.example.demo.controller;

import com.example.demo.dto.input.OperationInputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.exception.BankingException;
import com.example.demo.service.implementationLayer.OperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operation")
public class OperationController {
	@Autowired
	private OperationService operationService;

	@PostMapping("/deposit")
	public ResponseEntity<OperationOutputDto> deposit(@RequestBody OperationInputDto operation) {
		try {
			return new ResponseEntity<>(operationService.depositForAccount(operation), HttpStatus.OK);
		} catch (BankingException bankingException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/withdraw")
	public ResponseEntity<OperationOutputDto> withdraw(@RequestBody OperationInputDto operation) {
		try {
			return new ResponseEntity<>(operationService.withdrawFromAccount(operation), HttpStatus.OK);
		} catch (BankingException bankingException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}