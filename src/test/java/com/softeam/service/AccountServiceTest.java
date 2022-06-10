package com.softeam.service;


import com.softeam.model.Account;
import com.softeam.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Test;

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



   
    
   
}
