package com.eteration.simplebanking.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;

// This class is a place holder you can change the complete implementation

@Entity
@Table(name = "account")
public class Account {

    public Account() {
        this.transactions = new ArrayList<>();
        this.date = new Date();
    }

    public Account(String owner, String accountNumber) {
        this();
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public Account(String owner, String accountNumber, double balance) {
        this();
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(Long id, String owner, String accountNumber, double balance, Date date, List<Transaction> transactions) {
        this();
        this.id = id;
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.date = date;
        this.transactions = transactions;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner;

    private String accountNumber;

    private double balance;

    private Date date;

    @OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)
    @JoinColumn( name = "account_id", referencedColumnName = "id")
    private List<Transaction> transactions;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // transaction operations
    public void post(DepositTransaction transaction) {
        this.deposit(transaction.getAmount());
        this.transactions.add(transaction);
    }

    public void post(WithdrawalTransaction transaction) throws InsufficientBalanceException {
        this.withdraw(transaction.getAmount());
        this.transactions.add(transaction);
    }

    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (this.balance >= amount) {
            this.balance = this.balance - amount;
        } else {
            throw new InsufficientBalanceException();
        }
    }

}
