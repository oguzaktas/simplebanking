package com.eteration.simplebanking.model;

public class PhoneBillPaymentTransaction extends BillPaymentTransaction {

    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount) {
        super(payee, amount);
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
