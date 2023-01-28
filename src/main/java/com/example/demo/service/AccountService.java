package com.example.demo.service;

import com.example.demo.dto.ouput.AccountOutputDto;
import com.example.demo.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public AccountOutputDto getAccountDetailById(final Long accountId){
        return AccountOutputDto.builder().build();
    }

}
