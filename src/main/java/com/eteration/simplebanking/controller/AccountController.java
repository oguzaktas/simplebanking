package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is a place holder you can change the complete implementation
@RestController
public class AccountController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/account/v1/{accountNumber}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAccount(@PathVariable String accountNumber) {
        try {
            Account existingAccount = accountService.findAccount(accountNumber);
            return ResponseEntity.status(HttpStatus.OK).body(existingAccount);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/account/v1/credit/{accountNumber}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity credit(@PathVariable String accountNumber, @RequestBody DepositTransaction transaction) {
        try {
            Account existingAccount = accountService.findAccount(accountNumber);
            String approvalCode = transactionService.getApprovalCode();
            transaction.setApprovalCode(approvalCode);
            if (existingAccount == null){
                Account newAccount = new Account("Unknown Owner", accountNumber);
                newAccount.post(transaction);
                accountService.saveAccount(newAccount);
            } else {
                existingAccount.post(transaction);
                accountService.saveAccount(existingAccount);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new TransactionStatus("OK", transaction.getApprovalCode()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @RequestMapping(path = "/account/v1/debit/{accountNumber}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction transaction)
            throws InsufficientBalanceException {
        try {
            Account existingAccount = accountService.findAccount(accountNumber);
            String approvalCode = transactionService.getApprovalCode();
            transaction.setApprovalCode(approvalCode);
            if (existingAccount == null){
                Account newAccount = new Account("Unknown Owner", accountNumber);
                newAccount.post(transaction);
                accountService.saveAccount(newAccount);
            } else {
                existingAccount.post(transaction);
                accountService.saveAccount(existingAccount);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new TransactionStatus("OK", transaction.getApprovalCode()));

        } catch (InsufficientBalanceException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            throw new InsufficientBalanceException();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}