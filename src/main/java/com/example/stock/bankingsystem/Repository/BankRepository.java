package com.example.stock.bankingsystem.Repository;

import com.example.stock.bankingsystem.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
