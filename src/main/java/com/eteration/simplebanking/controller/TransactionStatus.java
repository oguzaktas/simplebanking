package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

public class TransactionStatus {

    public TransactionStatus(String status, String approvalCode){
        this.status = status;
        this.approvalCode = approvalCode;
    }

    private String status;
    private String approvalCode;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

}
