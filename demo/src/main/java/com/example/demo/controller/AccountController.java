package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public String listUserAccounts(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("accounts", accountService.getUserAccounts(userId));
        return "account-list"; // Thymeleaf の account-list.html に遷移
    }

    @GetMapping("/user/{userId}/new")
    public String showCreateAccountForm(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("account", new Account());
        return "account-form"; // Thymeleaf の account-form.html に遷移
    }

    @PostMapping("/user/{userId}")
    public String createAccount(@PathVariable Long userId, @ModelAttribute Account account) {
        accountService.createAccount(userId, account);
        return "redirect:/accounts/user/" + userId;
    }

    @GetMapping("/{id}/edit")
    public String showEditAccountForm(@PathVariable Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        model.addAttribute("user", account.getUser());
        return "account-form";
    }

    @PostMapping("/{id}")
    public String updateAccount(@PathVariable Long id, @ModelAttribute Account account) {
        accountService.updateAccount(id, account.getBalance());
        return "redirect:/accounts/user/" + account.getUser().getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteAccount(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        accountService.deleteAccount(id);
        return "redirect:/accounts/user/" + account.getUser().getId();
    }
}