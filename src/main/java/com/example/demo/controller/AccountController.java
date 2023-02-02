package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.ouput.AccountOutputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{accountNumber}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountOutputDto> getAccountDetail(@PathVariable Long accountNumber) {
        return new ResponseEntity<>(accountService.getAccountDetailById(accountNumber), HttpStatus.OK);
    }


//    @GetMapping(value = "/{accountNumber/operation}",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<OperationOutputDto>> getAccountOperations(@PathVariable Long accountNumber) {
//        return new ResponseEntity<>(accountService.getOperationsForAccount(accountNumber), HttpStatus.OK);
//    }

}
