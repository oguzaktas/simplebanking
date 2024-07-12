package com.eteration.simplebanking.services;

import com.eteration.simplebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public String getApprovalCode() {
        boolean codeNotFound = true;
        String generatedCode = "";
        while (codeNotFound){
            generatedCode = UUID.randomUUID().toString();
            codeNotFound = transactionRepository.existsByApprovalCode(generatedCode);
        }
        return generatedCode;
    }

}
