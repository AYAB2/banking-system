package com.example.stock.bankingsystem.Repository;

import com.example.stock.bankingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
