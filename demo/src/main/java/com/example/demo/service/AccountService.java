package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account createAccount(Long userId,Account account){
        User user = userRepository.findById(userId).orElseThrow(() 
                    -> new RuntimeException("User not found"));
        account.setUser(user);
        return accountRepository.save(account);
    }

    // public List<Account> getAllAccounts(Long userId){
    //     return accountRepository.findAllById(userId);
    // }

    public List<Account> getUserAccounts(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() 
                    -> new RuntimeException("User not found"));
        return accountRepository.findByUser(user);
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    public Account updateAccount(Long id,double newBalance){
        Account Account = accountRepository.findById(id).orElseThrow(() 
                    -> new RuntimeException("Account Not Found"));
        Account.setBalance(newBalance);
        return accountRepository.save(Account);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }

}
