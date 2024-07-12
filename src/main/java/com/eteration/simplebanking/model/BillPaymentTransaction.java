package com.eteration.simplebanking.model;

public class BillPaymentTransaction extends WithdrawalTransaction {

    public BillPaymentTransaction(String payee, double amount) {
        super(amount);
        this.payee = payee;
    }

    private String payee;

    // getters and setters
    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }
}
