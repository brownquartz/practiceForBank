package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="accounts")
public class Account {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique=true)
    private String accountNumber;

    @Column(nullable = false)
    private Double balance;

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    public Account (){}

    public Account(String accountNumber,Double balance,User user){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    // Idはセットする必要ない
    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

} 
