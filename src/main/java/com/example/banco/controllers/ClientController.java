package com.example.banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.services.ClientService;

import dto.ClientRequestDTO;
import dto.ClientResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/clients")
@Tag(name= "Clients", description="Client management endpoints")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Create a new client with an associated account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Client created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "CPF or email already exists")
    })
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO dto) {
        ClientResponseDTO response = clientService.createClient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClient(@PathVariable Long id) {
        ClientResponseDTO response = clientService.getClientById(id);
        return ResponseEntity.ok(response);
    }
}
