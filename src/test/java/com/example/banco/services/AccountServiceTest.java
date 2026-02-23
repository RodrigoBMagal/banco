package com.example.banco.services;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.banco.entities.Account;
import com.example.banco.entities.Client;
import com.example.banco.exception.AccountNotFoundException;
import com.example.banco.repository.AccountRepository;

import dto.BalanceResponseDTO;

@ExtendWith(MockitoExtension.class)
@DisplayName("Account Service Test")
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;
    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client("John Doe", "12345678901","john@example.com");
        client.setId(1L);

        account = new Account("ABC12345", client);
        account.setId(1L);
        account.setBalance(new BigDecimal("1000.00"));
    }

    @Test
    @DisplayName("Should return account by ID")
    void shouldReturnAccountById() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        var response = accountService.getAccountById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("ABC12345", response.getAccountNumber());
        assertEquals(new BigDecimal("1000.00"), response.getBalance());
        assertEquals("John Doe", response.getClientName());

        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when account not found")
    void shouldThrowExceptionWhenAccountNotFound() {
        when(accountRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountById(999L);
        });

        verify(accountRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Should return account balance")
    void shouldReturnAccountBalance() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        BalanceResponseDTO response = accountService.getAccountBalance(1L);

        assertNotNull(response);
        assertEquals(1L, response.getAccountId());
        assertEquals(new BigDecimal("1000.00"), response.getBalance());
    }

    @Test
    @DisplayName("Should generate unique account number")
    void shouldGenerateUniqueAccountNumber() {
        String accountNumber1 = accountService.generateAccountNumber();
        String accountNumber2 = accountService.generateAccountNumber();

        assertNotNull(accountNumber1);
        assertNotNull(accountNumber2);
        assertEquals(8, accountNumber1.length());
        assertNotEquals(accountNumber1, accountNumber2);
    }

}
