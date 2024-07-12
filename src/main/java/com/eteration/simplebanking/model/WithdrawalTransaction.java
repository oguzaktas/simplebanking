package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction() {
        this.setType(TransactionType.WithdrawalTransaction);
    }

    public WithdrawalTransaction(double amount) {
        this.setAmount(amount);
    }

}
