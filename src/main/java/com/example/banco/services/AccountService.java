package com.example.banco.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banco.entities.Account;
import com.example.banco.exception.AccountNotFoundedException;
import com.example.banco.repository.AccountRepository;

import dto.AccountResponseDTO;
import dto.BalanceResponseDTO;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public AccountResponseDTO getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundedException(accountId));
        
            return new AccountResponseDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getClient().getId(),
                account.getClient().getName()
                
            );
    }

    @Transactional(readOnly = true)
    public BalanceResponseDTO getAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundedException(accountId));
            
        return new BalanceResponseDTO(
            account.getId(),
            account.getBalance()
            account.getAccountNumber()
        );
    }

    public String generateAccountNumber(){
        return UUID.randomUUID().toString().substring(0, 8 ).toUpperCase();
    }
}
