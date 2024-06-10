package com.example.stock.bankingsystem.service;

import com.example.stock.bankingsystem.Repository.OperationHistoryRepository;
import com.example.stock.bankingsystem.models.BankAccount;
import com.example.stock.bankingsystem.Repository.BankAccountRepository;
import com.example.stock.bankingsystem.models.OperationHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private OperationHistoryRepository operationHistoryRepository;

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public List<OperationHistory> getOperationHistory(Long accountId) {
        return operationHistoryRepository.findByAccountId(accountId);
    }

    public BankAccount withdraw(Long id, double amount) {
        Optional<BankAccount> existingAccount = bankAccountRepository.findById(id);
        if (existingAccount.isPresent()) {
            BankAccount account = existingAccount.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                bankAccountRepository.save(account);

                // Log operation
                OperationHistory history = new OperationHistory();
                history.setAccountId(id);
                history.setOperationType("withdraw");
                history.setAmount(amount);
                history.setDescription("Withdrawal");
                history.setTimestamp(LocalDateTime.now());
                operationHistoryRepository.save(history);

                return account;
            }
        }
        return null;
    }

    public BankAccount transfer(Long fromAccountId, Long toAccountId, double amount) {
        Optional<BankAccount> fromAccountOpt = bankAccountRepository.findById(fromAccountId);
        Optional<BankAccount> toAccountOpt = bankAccountRepository.findById(toAccountId);

        if (fromAccountOpt.isPresent() && toAccountOpt.isPresent()) {
            BankAccount fromAccount = fromAccountOpt.get();
            BankAccount toAccount = toAccountOpt.get();

            if (fromAccount.getBalance() >= amount) {
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);

                bankAccountRepository.save(fromAccount);
                bankAccountRepository.save(toAccount);

                // Log transfer from "fromAccount"
                OperationHistory fromHistory = new OperationHistory();
                fromHistory.setAccountId(fromAccountId);
                fromHistory.setOperationType("transfer");
                fromHistory.setAmount(amount);
                fromHistory.setDescription("Transfer to account " + toAccountId);
                fromHistory.setTimestamp(LocalDateTime.now());
                operationHistoryRepository.save(fromHistory);

                // Log transfer to "toAccount"
                OperationHistory toHistory = new OperationHistory();
                toHistory.setAccountId(toAccountId);
                toHistory.setOperationType("transfer");
                toHistory.setAmount(amount);
                toHistory.setDescription("Transfer from account " + fromAccountId);
                toHistory.setTimestamp(LocalDateTime.now());
                operationHistoryRepository.save(toHistory);

                return fromAccount;
            }
        }
        return null;
    }

    public BankAccount updateBankAccount(BankAccount bankAccount) {
        Optional<BankAccount> existingAccount = bankAccountRepository.findById(bankAccount.getId());
        if (existingAccount.isPresent()) {
            return bankAccountRepository.save(bankAccount);
        } else {
            return null;
        }
    }

    public BankAccount getBankAccountById(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    public Iterable<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
