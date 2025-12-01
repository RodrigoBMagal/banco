package com.example.banco.exception;

public class AccountNotFoundedException extends RuntimeException {
    public AccountNotFoundedException(Long accountId) {
        super("Account with ID " + accountId + " not found.");
    }

    public AccountNotFoundedException(String accountNumber) {
        super("Account with number " + accountNumber + " not found.");
    }
}
