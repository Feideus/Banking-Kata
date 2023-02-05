package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DataInitializer implements CommandLineRunner {

	private AccountRepository accountRepository;

	@Override
	public void run(String... args) {
		accountRepository.save(new Account(null,"0000001",0.00,1000L));
		accountRepository.save(new Account(null,"0000002",10.00,1010L));
		accountRepository.save(new Account(null,"0000003",2000.00,1010L));
	}
}
