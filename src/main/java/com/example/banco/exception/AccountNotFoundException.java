package com.example.banco.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long accountId) {
        super("Account with ID " + accountId + " not found.");
    }

    public AccountNotFoundException(String accountNumber) {
        super("Account with number " + accountNumber + " not found.");
    }
}
