package com.example.banco.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {  // ❌ REMOVER "extends Client"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> transactions = new ArrayList<>();

    // Construtores
    public Account() {}

    public Account(String accountNumber, Client client) {
        this.accountNumber = accountNumber;
        this.client = client;
        this.balance = BigDecimal.ZERO;
    }

    // Métodos de negócio
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (!hasSufficientBalance(amount)) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance = this.balance.subtract(amount);
    }

    public boolean hasSufficientBalance(BigDecimal amount) {
        return this.balance.compareTo(amount) >= 0;
    }

    public void addTransaction(Transfer transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    
    public List<Transfer> getTransactions() { return transactions; }
    public void setTransactions(List<Transfer> transactions) { this.transactions = transactions; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && 
               Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber);
    }

    @Override
    public String toString() {
        return "Account{id=" + id + ", accountNumber='" + accountNumber + 
               "', balance=" + balance + "}";
    }
}