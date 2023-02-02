package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.ouput.AccountOutputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import com.example.demo.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public AccountOutputDto getAccountDetailById(final Long accountId){
        return AccountOutputDto.builder().build();
    }

    public List<OperationOutputDto> getOperationsForAccount(final Long accountNumber) {
        return new ArrayList<>();
    }

}
