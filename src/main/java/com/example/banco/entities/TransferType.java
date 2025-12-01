package com.example.banco.entities;

public enum TransferType {
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw"),
    SENT_TRANSFER("Sent Transfer"),
    RECEIVED_TRANSFER("Received Transfer");

    private final String description;

    TransferType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
