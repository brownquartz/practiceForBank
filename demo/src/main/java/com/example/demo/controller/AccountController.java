package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    public AccountController(AccountService accountService) {
        this.accountService = accountService;    
    }

    @PostMapping
    public String createAccount(@ModelAttribute Account account) {
        accountService.createAccount(account);
        return "redirect:/accounts";
    }

    @GetMapping("/users/{userId}")
    public List<Account> getAccountsByUserId(@PathVariable Long userId) {
        return accountService.getAccountsByUserId(userId);
    }
    
    

}
