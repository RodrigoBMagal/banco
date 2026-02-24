package com.example.banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banco.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    Optional<Client> findById(Long id);
    Optional<Client> findByCpf(String cpf);
    boolean existsById(Long id);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
