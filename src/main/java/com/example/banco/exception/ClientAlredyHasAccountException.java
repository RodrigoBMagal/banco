package com.example.banco.exception;

public class ClientAlredyHasAccountException extends RuntimeException {
    public ClientAlredyHasAccountException(Long clientId) {
        super("Client with ID " + clientId + " already has an account.");
    }

}
