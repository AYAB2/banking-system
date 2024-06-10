package com.example.stock.bankingsystem.service;

import com.example.stock.bankingsystem.Repository.BankRepository;
import com.example.stock.bankingsystem.models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public Bank createBank(Bank bank) {
        // Save the bank to the database
        return bankRepository.save(bank);
    }

    public Bank updateBank(Bank bank) {
        // Check if the bank exists in the database
        if (bankRepository.existsById(bank.getId())) {
            // Save the updated bank to the database
            return bankRepository.save(bank);
        } else {
            // Throw an exception or return null
            return null;
        }
    }

    public void deleteBank(Long id) {
        // Delete the bank from the database
        bankRepository.deleteById(id);
    }

    public Bank getBank(Long id) {
        // Get the bank from the database
        return bankRepository.findById(id).orElse(null);
    }
}
