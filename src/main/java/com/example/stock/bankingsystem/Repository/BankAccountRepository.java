package com.example.stock.bankingsystem.Repository;

import com.example.stock.bankingsystem.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
