package com.example.banco.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banco.entities.Account;
import com.example.banco.entities.Transfer;
import com.example.banco.entities.TransferType;
import com.example.banco.exception.AccountNotFoundException;
import com.example.banco.exception.InsufficientBalanceException;
import com.example.banco.exception.InvalidTransferException;
import com.example.banco.repository.AccountRepository;
import com.example.banco.repository.TransferRepository;

import dto.DepositRequestDTO;
import dto.WithdrawRequestDTO;
import dto.TransferRequestDTO;
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
                .orElseThrow(() -> new AccountNotFoundException(dto.getAccountId()));

        account.deposit(dto.getAmount());
        accountRepository.save(account);

        Transfer transfer = new Transfer(TransferType.DEPOSIT, dto.getAmount(), account);
        transferRepository.save(transfer);
    }

    @Transactional
    public void withdraw(WithdrawRequestDTO dto) {
        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException(dto.getAccountId()));

        if (!account.hasSufficientBalance(dto.getAmount())) {
            throw new InsufficientBalanceException();
        }

        account.withdraw(dto.getAmount());
        accountRepository.save(account);

        Transfer transfer = new Transfer(TransferType.WITHDRAW, dto.getAmount(), account);
        transferRepository.save(transfer);
    }

    @Transactional
    public void transfer(TransferRequestDTO dto) {
        // Validações
        if (dto.getSourceAccountId().equals(dto.getDestinationAccountId())) {
            throw new InvalidTransferException("Source and destination accounts cannot be the same");
        }

        Account sourceAccount = accountRepository.findById(dto.getSourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException(dto.getSourceAccountId()));

        Account destinationAccount = accountRepository.findById(dto.getDestinationAccountId())
                .orElseThrow(() -> new AccountNotFoundException(dto.getDestinationAccountId()));

        if (!sourceAccount.hasSufficientBalance(dto.getAmount())) {
            throw new InsufficientBalanceException();
        }

        // Realizar transferência
        sourceAccount.withdraw(dto.getAmount());
        destinationAccount.deposit(dto.getAmount());

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        // Registrar transações
        Transfer sentTransfer = new Transfer(TransferType.SENT_TRANSFER, dto.getAmount(), sourceAccount);
        sentTransfer.setAccountDestinationId(destinationAccount.getId());
        transferRepository.save(sentTransfer);

        Transfer receivedTransfer = new Transfer(TransferType.RECEIVED_TRANSFER, dto.getAmount(), destinationAccount);
        receivedTransfer.setAccountDestinationId(sourceAccount.getId());
        transferRepository.save(receivedTransfer);
    }

    @Transactional(readOnly = true)
    public StatementResponseDTO getAccountStatement(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        List<Transfer> transfers = transferRepository.findByAccountId(accountId);

        List<StatementResponseDTO.TransferRequestDTO> transferDTOs = transfers.stream()
                .map(t -> new StatementResponseDTO.TransferRequestDTO(
                        t.getId(),
                        t.getType(),
                        t.getAmount(),
                        t.getTimestamp(),
                        t.getAccountDestinationId()
                ))
                .collect(Collectors.toList());

        return new StatementResponseDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                transferDTOs
        );
    }
}