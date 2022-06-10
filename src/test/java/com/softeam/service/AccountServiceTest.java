package com.softeam.service;


import com.softeam.model.Account;
import com.softeam.model.Transaction;
import com.softeam.model.TransactionType;
import com.softeam.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;


public class AccountServiceTest {

	AccountRepository accountRepository = mock(AccountRepository.class);
    private AccountService accountService = new AccountServiceImp(accountRepository);
    
    
    @Test
    public void testCreateAccount() {
    	String firstName = "firstname";
    	String lastName =  "lastname";
    	String expectedAccountId = "20";
    	when(accountRepository.create(firstName, lastName)).thenReturn("20");
    	
    	String accountId = accountService.createAccount(firstName, lastName);
    	Assert.assertEquals(accountId,expectedAccountId);
    	
    }
    
    @Test
    public void testGetAccount() {
    	String accountId ="20";
    	Account expectedAccount = new Account("firstname", "lastname");
    	expectedAccount.setId(accountId);
    	when(accountRepository.getById(accountId)).thenReturn(expectedAccount);
    	
    	Account account = accountService.getAccount(accountId);
    	
    	Assert.assertEquals(account,expectedAccount);
    	
    }

    @Test
    public void testDeposit() {
    	String accountId ="20-111-3333";
		BigDecimal initialBlance = new BigDecimal(5);
		BigDecimal depositAmount = new BigDecimal(10);
		BigDecimal expectedBalanceAfterDeposit = depositAmount.add(initialBlance);

    	Account expectedAccount = new Account("firstname", "lastname");
    	expectedAccount.setId(accountId);
    	expectedAccount.setBalance(initialBlance);

    	int numberTransactionBeforeDeposit = expectedAccount.getTransactionList().size();
    	int numberTransactionAfterDeposit = numberTransactionBeforeDeposit +1;


        when(accountRepository.getById(accountId)).thenReturn(expectedAccount);

        accountService.deposit(accountId, depositAmount);

        //check balance after deposit
        Assert.assertEquals(expectedAccount.getBalance().intValue(), expectedBalanceAfterDeposit.intValue(), 0);


        //check deposit transaction
        Assert.assertEquals(expectedAccount.getTransactionList().size(), numberTransactionAfterDeposit, 0);

        Transaction depositTransaction = expectedAccount.getTransactionList().get(numberTransactionAfterDeposit - 1);

        Assert.assertEquals(depositTransaction.getType(), TransactionType.DEPOSIT);
        Assert.assertEquals(depositTransaction.getAmount().intValue(), depositAmount.intValue(), 0);
        Assert.assertEquals(depositTransaction.getInitialBalance().intValue(), initialBlance.intValue(), 0);

        verify(accountRepository).save(any());
    }



   
    
   
}
