package com.example.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banco.entities.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>{
    List<Transfer> findByAccountId(Long accountId);
}
