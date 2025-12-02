package com.example.banco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banco.entities.Account;
import com.example.banco.entities.Transfer;
import com.example.banco.entities.TransferType;
import com.example.banco.exception.AccountNotFoundedException;
import com.example.banco.exception.InsufficientBalanceException;
import com.example.banco.repository.AccountRepository;
import com.example.banco.repository.TransferRepository;

import dto.DepositRequestDTO;
import dto.StatementResponseDTO;

@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Transactional
    public void deposit(DepositRequestDTO dto) {
        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new AccountNotFoundedException(dto.getAccountId()));

        account.setBalance(account.getBalance().add(dto.getAmount()));
        accountRepository.save(account);

        Transfer transfer = new Transfer(TransferType.DEPOSIT, dto.getAmount(), account);
        transferRepository.save(transfer);
    }

    @Transactional
    public void withdraw(DepositRequestDTO dto) {
        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new AccountNotFoundedException(dto.getAccountId()));

        account.setBalance(account.getBalance().add(dto.getAmount()));
        accountRepository.save(account);

        Transfer transfer = new Transfer(TransferType.WITHDRAW, dto.getAmount(), account);
        transferRepository.save(transfer);
    }

    @Transactional
    public void transfer(DepositRequestDTO dto, Long toAccountId) {
        if (dto.getAccountId().equals(toAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        Account fromAccount = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new AccountNotFoundedException(dto.getAccountId()));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountNotFoundedException(toAccountId));

        if (fromAccount.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientBalanceException();
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(dto.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(dto.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        
    }

    @Transactional(readOnly = true)
    public StatementResponseDTO getAccountStatement(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundedException(accountId));

        List<Transfer> transactions = transferRepository.findByAccountId(accountId);

        List<StatementResponseDTO.TransferRequestDTO> transfersDTO = transactions.stream()
            .map(t -> new StatementResponseDTO.TransferRequestDTO(
                t.getId(),
                t.getType(),
                t.getAmount(),
                t.getTimestamp(),
                t.getDestinationAccountId()
                ))
            .toList();
        return new StatementResponseDTO(
            account.getId(),
            account.getAccountNumber(),
            account.getBalance(),
            transfersDTO
        );
    }
}
