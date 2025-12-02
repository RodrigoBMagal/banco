package dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.banco.entities.TransferType;

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
    
    public static class TransferRequestDTO {
        private Long id;
        private TransferType type;
        private BigDecimal amount;
        private LocalDateTime timestamp;
        private Long destinationAccountId;

        public TransferRequestDTO() {
        }

        public TransferRequestDTO(Long id, TransferType type, BigDecimal amount, LocalDateTime timestamp, Long destinationAccountId) {
            this.id = id;
            this.type = type;
            this.amount = amount;
            this.timestamp = timestamp;
            this.destinationAccountId = destinationAccountId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id){
            this.id = id;
        }

        public TransferType getType() {
            return type;
        }

        public void setType(TransferType type) {
            this.type = type;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public Long getDestinationAccountId() {
            return destinationAccountId;
        }

        public void setDestinationAccountId(Long destinationAccountId) {
            this.destinationAccountId = destinationAccountId;
        }

            
    }
}
