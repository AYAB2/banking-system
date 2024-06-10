package com.example.stock.bankingsystem.Repository;

import com.example.stock.bankingsystem.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
