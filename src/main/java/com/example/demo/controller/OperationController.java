package com.example.demo.controller;

import com.example.demo.dto.input.OperationInputDto;
import com.example.demo.dto.ouput.AccountOutputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.service.AccountService;
import com.example.demo.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operation")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @GetMapping(value = "/{accountNumber}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OperationOutputDto>> getAccountOperations(@PathVariable Long accountNumber) {
        return new ResponseEntity<>(operationService.getOperationsForAccount(accountNumber), HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public OperationOutputDto deposit(@RequestBody OperationInputDto operation) {
        operationService.depositForAccount(operation);
        return operation;
    }

    @PostMapping("/withdraw")
    public OperationOutputDto withdraw(@RequestBody OperationInputDto operation) {
        operationService.withdrawFromAccount(operation);
        return operation;
    }

}