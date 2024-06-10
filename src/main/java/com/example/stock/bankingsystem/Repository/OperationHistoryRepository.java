package com.example.stock.bankingsystem.Repository;

import com.example.stock.bankingsystem.models.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {
    List<OperationHistory> findByAccountId(Long accountId);
}
