package com.example.banco.entities;

public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawl"),
    SENT_TRANSFER("Sent Transfer"),
    RECEIVED_TRANSFER("Received Transfer");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
