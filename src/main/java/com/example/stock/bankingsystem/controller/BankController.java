package com.example.stock.bankingsystem.controller;

import com.example.stock.bankingsystem.models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.stock.bankingsystem.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/createbank")
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        return ResponseEntity.ok(bankService.createBank(bank));
    }

    @PutMapping("/update")
    public ResponseEntity<Bank> updateBank(@RequestBody Bank bank) {
        Bank updatedBank = bankService.updateBank(bank);
        if (updatedBank != null) {
            return ResponseEntity.ok(updatedBank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getBank/{id}")
    public ResponseEntity<Bank> getBank(@PathVariable Long id) {
        Bank bank = bankService.getBank(id);
        if (bank != null) {
            return ResponseEntity.ok(bank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
        return ResponseEntity.ok().build();
    }
}
