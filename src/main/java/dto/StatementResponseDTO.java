package dto;

import java.math.BigDecimal;
import java.util.List;

public class StatementResponseDTO {

    private Long accountId;
    private String accountNumber;
    private BigDecimal actualBalance;
    private List<TransferRequestDTO> transfers;

    public StatementResponseDTO() {
    }

    public StatementResponseDTO(Long accountId, String accountNumber, BigDecimal actualBalance, List<TransferRequestDTO> transfers) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.actualBalance = actualBalance;
        this.transfers = transfers;
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

    public BigDecimal getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(BigDecimal actualBalance) {
        this.actualBalance = actualBalance;
    }

    public List<TransferRequestDTO> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<TransferRequestDTO> transfers) {
        this.transfers = transfers;
    }
    
}
