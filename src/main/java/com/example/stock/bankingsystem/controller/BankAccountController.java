package com.example.stock.bankingsystem.controller;

import com.example.stock.bankingsystem.models.BankAccount;
import com.example.stock.bankingsystem.models.OperationHistory;
import com.example.stock.bankingsystem.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/createbankAccount")
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        return ResponseEntity.ok(bankAccountService.createBankAccount(bankAccount));
    }

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        bankAccount.setId(id);
        BankAccount updatedBankAccount = bankAccountService.updateBankAccount(bankAccount);
        if (updatedBankAccount != null) {
            return ResponseEntity.ok(updatedBankAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getbankAccount/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long id) {
        BankAccount bankAccount = bankAccountService.getBankAccountById(id);
        if (bankAccount != null) {
            return ResponseEntity.ok(bankAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<OperationHistory>> getOperationHistory(@PathVariable Long accountId) {
        List<OperationHistory> history = bankAccountService.getOperationHistory(accountId);
        if (history != null) {
            return ResponseEntity.ok(history);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getallbankAccounts")
    public ResponseEntity<Iterable<BankAccount>> getAllBankAccounts() {
        return ResponseEntity.ok(bankAccountService.getAllBankAccounts());
    }

    @DeleteMapping("/deleteBankAccount/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<BankAccount> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        BankAccount updatedBankAccount = bankAccountService.withdraw(id, amount);
        if (updatedBankAccount != null) {
            return ResponseEntity.ok(updatedBankAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<BankAccount> transfer(@RequestBody Map<String, String> payload) {
        try {
            Long fromAccountId = Long.parseLong(payload.get("fromAccountId"));
            Long toAccountId = Long.parseLong(payload.get("toAccountId"));
            double amount = Double.parseDouble(payload.get("amount"));

            BankAccount fromAccount = bankAccountService.transfer(fromAccountId, toAccountId, amount);
            if (fromAccount != null) {
                return ResponseEntity.ok(fromAccount);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
