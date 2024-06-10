package com.example.stock.bankingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/index.html")
    public String index() {
        return "index";
    }

    @GetMapping("/User/UserView.html")
    public String createUser() {
        return "/User/UserView";
    }


    @GetMapping("/User/AddUser.html")
    public String addUser() {
        return "/User/AddUser";
    }

    @GetMapping("/User/UpdateUser.html")
    public String updateUser() {
        return "/User/UpdateUser";
    }

    @GetMapping("/Admin/AdminView.html")
    public String createAdmin() {
        return "/Admin/AdminView";
    }

    @GetMapping("/Admin/AddAdmin.html")
    public String addAdmin() {
        return "/Admin/AddAdmin";
    }

    @GetMapping("/Admin/UpdateAdmin.html")
    public String updateAdmin() {
        return "/Admin/UpdateAdmin";
    }

    @GetMapping("/Bank/BankView.html")
    public String createBank() {
        return "/Bank/BankView";
    }

    @GetMapping("/Bank/Bank.html")
    public String addBank() {
        return "/Bank/Bankinformations";
    }

    @GetMapping("/Bank/UpdateBank.html")
    public String updateBank() {
        return "/Bank/UpdateBank";
    }

    @GetMapping("/BankAccount/BankAccountList.html")
    public String createBankAccount() {
        return "/BankAccount/BankAccountList";
    }

    @GetMapping("/BankAccount/AddBankAccount.html")
    public String addBankAccount() {
        return "/BankAccount/AddBankAccount";
    }

    @GetMapping("/Bank/Bankinformations.html")
    public String displayBank() {
        return "/Bank/BankInformations";
    }

    @GetMapping("/BankAccount/BankAccountOperations.html")
    public String bankAccountOperations() {
        return "/BankAccount/BankAccountOperations";
    }
    @GetMapping("/BankAccount/BankOperationsHistory.html")
    public String bankOperationsHistory() {
        return "/BankAccount/BankOperationsHistory";
    }

    @GetMapping("/BankAccount/EditBankAccount.html")
    public String updateBankAccount() {
        return "/BankAccount/EditBankAccount";
    }
}
