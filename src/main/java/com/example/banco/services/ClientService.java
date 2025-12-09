package com.example.banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banco.entities.Account;
import com.example.banco.entities.Client;
import com.example.banco.exception.ClientAlredyHasAccountException;
import com.example.banco.exception.ClientNotFoundException;
import com.example.banco.repository.ClientRepository;

import dto.ClientRequestDTO;
import dto.ClientResponseDTO;

import java.math.BigDecimal;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public ClientResponseDTO createClient(ClientRequestDTO dto) {
        // Verificar se CPF já existe
        if (clientRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF already exists");
        }

        // Criar cliente
        Client client = new Client(dto.getName(), dto.getCpf(), dto.getEmail());
        
        // Criar conta automaticamente
        String accountNumber = accountService.generateAccountNumber();
        Account account = new Account(accountNumber, client);
        
        client.setAccount(account);
        Client savedClient = clientRepository.save(client);

        return new ClientResponseDTO(
            savedClient.getId(),
            savedClient.getName(),
            savedClient.getCpf(),
            savedClient.getEmail(),
            savedClient.getAccount().getId(),
            savedClient.getAccount().getAccountNumber()
        );
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new ClientNotFoundException(id));

        return new ClientResponseDTO(
            client.getId(),
            client.getName(),
            client.getCpf(),
            client.getEmail(),
            client.getAccount().getId(),
            client.getAccount().getAccountNumber()
        );
    }
}