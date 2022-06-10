package com.softeam;

import com.softeam.model.Account;
import com.softeam.model.Transaction;
import com.softeam.model.TransactionType;
import com.softeam.repository.AccountRepository;
import com.softeam.repository.AccountRepositoryImp;
import com.softeam.service.AccountService;
import com.softeam.service.AccountServiceImp;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankTestFunctional {
	
	private Map<String, Account> dummyAccountList = new HashMap<> ();
	private AccountRepository accountRepository = new AccountRepositoryImp(dummyAccountList);
	private AccountService accountService = new AccountServiceImp(accountRepository);

	 @Test
	    public void testAccountRepository() {
	    	String firstName = "firstname";
	    	String lastName =  "lastname";
	    	BigDecimal changedBalance = new BigDecimal(3);
	    	
	    	String acountId = accountRepository.create(firstName, lastName);
	    
	    	Account account = accountRepository.getById(acountId);
	    	
	    	Assert.assertEquals(account.getFirstName(),firstName);
	    	Assert.assertEquals(account.getLastName(),lastName);
	    	Assert.assertEquals(account.getBalance().intValue(),0,0);
	    	Assert.assertEquals(account.getTransactionList().size(),0,0);
	    	
	    	account.setBalance(changedBalance);
	    	accountRepository.save(account);
	    	
	    	Assert.assertEquals(accountRepository.getById(acountId).getBalance().intValue(),changedBalance.intValue(),0);
	    	
	    }
	 
	 
	 @Test
	    public void testAccountService() {
		 
		 	String accountId = accountService.createAccount("firstname", "lastname");
	        accountService.deposit(accountId, new BigDecimal(20));

	        accountService.withdraw(accountId, new BigDecimal(5));

	        // check account balance
	        Assert.assertEquals(15, accountService.getAccount(accountId).getBalance().intValue(), 0);

	        // check transactions
	        List<Transaction> transactionList = accountService.getTransactions(accountId);
	        Assert.assertEquals(transactionList.get(0).getInitialBalance().intValue(), 0, 0);
	        Assert.assertEquals(transactionList.get(0).getAmount().intValue(), 20, 0);
	        Assert.assertEquals(transactionList.get(0).getType(), TransactionType.DEPOSIT);


	        Assert.assertEquals(transactionList.get(1).getInitialBalance().intValue(), 20, 0);
	        Assert.assertEquals(transactionList.get(1).getAmount().intValue(), 5, 0);
	        Assert.assertEquals(transactionList.get(1).getType(), TransactionType.WITHDRAWAL);
	 }
	 
	
	
}
