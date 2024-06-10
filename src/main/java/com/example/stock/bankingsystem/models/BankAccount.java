package com.example.stock.bankingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.Random;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private double balance;

    private Long phoneNumber;

    private String address;

    @PrePersist
    public void generateAccountNumber() {
        this.accountNumber = new Random().nextLong();
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void transfer(BankAccount targetAccount, double amount) {
        this.withdraw(amount);
        targetAccount.deposit(amount);
    }
}