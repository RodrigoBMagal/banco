package dto;

import java.math.BigDecimal;

public class BalanceResponseDTO {

    private Long accountId;
    private String accountNumber;
    private BigDecimal balance;

    public BalanceResponseDTO() {
    }

    public BalanceResponseDTO(Long accountId, String accountNumber, BigDecimal balance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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


}
