package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation
public class DepositTransaction extends Transaction {

    public DepositTransaction() {
        this.setType(TransactionType.DepositTransaction);
    }

    public DepositTransaction(double amount) {
        this.setAmount(amount);
    }

}
