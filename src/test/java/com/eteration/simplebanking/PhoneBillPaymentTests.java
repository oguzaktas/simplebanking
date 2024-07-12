package com.eteration.simplebanking;

import com.eteration.simplebanking.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class PhoneBillPaymentTests {

    @Test
    public void testPhoneBillPayment() throws InsufficientBalanceException {
        Account account = new Account("Jim", "12345");
        account.post(new DepositTransaction(1000));
        account.post(new WithdrawalTransaction(200));
        account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));
        assertEquals(account.getBalance(), 703.50, 0.0001);
    }

}