package com.example.banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.banco.services.AccountService;
import com.example.banco.services.TransferService;

import dto.AccountResponseDTO;

@RequesController
@RequestMapping("/accounts")
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
}
