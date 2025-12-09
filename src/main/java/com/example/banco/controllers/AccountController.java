package com.example.banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.services.AccountService;
import com.example.banco.services.TransferService;

import dto.AccountResponseDTO;
import dto.BalanceResponseDTO;
import dto.StatementResponseDTO;

@RestController  // ✅ CORRIGIDO
@RequestMapping("/api/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransferService transferService;

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> searchAccount(@PathVariable Long accountId) {
        AccountResponseDTO dto = accountService.getAccountById(accountId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BalanceResponseDTO> getBalance(@PathVariable Long accountId) {
        BalanceResponseDTO dto = accountService.getAccountBalance(accountId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{accountId}/statement")
    public ResponseEntity<StatementResponseDTO> getStatement(@PathVariable Long accountId) {
        StatementResponseDTO dto = transferService.getAccountStatement(accountId);
        return ResponseEntity.ok(dto);
    }
}