package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccountById(long id){
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAccountsByUserId(long userId){
        return accountRepository.findByUserId(userId);
    }

    public Account updateAccount(Long id,Account accountDetails){
        Account account = accountRepository.findById(id).orElseThrow(() 
            -> new RuntimeException());
        account.setAccountNumber(accountDetails.getAccountNumber());
        account.setBalance(accountDetails.getBalance());
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }

}
