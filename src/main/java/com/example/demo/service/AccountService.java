package com.example.demo.service;

import com.example.demo.dto.OperationInputDto;
import com.example.demo.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public Account getAccountDetailById(final Long accountId){
        return Account.builder().build();
    }

}
