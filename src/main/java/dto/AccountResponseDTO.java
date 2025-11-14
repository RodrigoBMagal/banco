package dto;

import java.math.BigDecimal;

public class AccountResponseDTO {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private Long clientId;
    private String clientName;

    public AccountResponseDTO() {
    }

    public AccountResponseDTO(Long id, String accountNumber, BigDecimal balance, Long clientId, String clientName) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
