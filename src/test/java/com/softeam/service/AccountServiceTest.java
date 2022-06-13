package com.softeam.service;


import com.softeam.exception.InsufficientBalanceException;
import com.softeam.model.Account;
import com.softeam.model.Transaction;
import com.softeam.model.TransactionType;
import com.softeam.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class AccountServiceTest {

    AccountRepository accountRepository = mock(AccountRepository.class);
    private AccountService accountService = new AccountServiceImp(accountRepository);


    @Test
    public void testGetAccount() {
        String accountId = "20";
        Account expectedAccount = new Account("firstname", "lastname");
        expectedAccount.setId(accountId);
        when(accountRepository.getById(accountId)).thenReturn(expectedAccount);

        Account account = accountService.getAccount(accountId);

        Assert.assertEquals(account, expectedAccount);

    }

    @Test
    public void testDeposit() {
        String accountId = "20-111-3333";
        BigDecimal initialBlance = new BigDecimal(5);
        BigDecimal depositAmount = new BigDecimal(10);
        BigDecimal expectedBalanceAfterDeposit = depositAmount.add(initialBlance);

        Account expectedAccount = new Account("firstname", "lastname");
        expectedAccount.setId(accountId);
        expectedAccount.setBalance(initialBlance);

        int numberTransactionBeforeDeposit = expectedAccount.getTransactionList().size();
        int numberTransactionAfterDeposit = numberTransactionBeforeDeposit + 1;


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

    @Test
    public void testWithdrawal() {
        String accountId = "20-111-3333";
        BigDecimal initialBlance = new BigDecimal(10);
        BigDecimal withdrawaAmount = new BigDecimal(5);
        BigDecimal expectedBalanceAfterWithdraw = initialBlance.subtract(withdrawaAmount);

        Account expectedAccount = new Account("firstname", "lastname");
        expectedAccount.setId(accountId);
        expectedAccount.setBalance(initialBlance);

        int numberTransactionBeforeWithdrawal = expectedAccount.getTransactionList().size();
        int numberTransactionAfterWithdrawal = numberTransactionBeforeWithdrawal + 1;


        when(accountRepository.getById(accountId)).thenReturn(expectedAccount);

        accountService.withdraw(accountId, withdrawaAmount);

        //check balance after withdraw
        Assert.assertEquals(expectedAccount.getBalance().intValue(), expectedBalanceAfterWithdraw.intValue(), 0);


        //check withdraw transaction
        Assert.assertEquals(expectedAccount.getTransactionList().size(), numberTransactionAfterWithdrawal, 0);

        Transaction depositTransaction = expectedAccount.getTransactionList().get(numberTransactionAfterWithdrawal - 1);

        Assert.assertEquals(depositTransaction.getType(), TransactionType.WITHDRAWAL);
        Assert.assertEquals(depositTransaction.getAmount().intValue(), withdrawaAmount.intValue(), 0);
        Assert.assertEquals(depositTransaction.getInitialBalance().intValue(), initialBlance.intValue(), 0);


        verify(accountRepository).save(any());

    }


    /**
     * should throw exception because account balance is set to zero for new account and no deposit is done
     */
    @Test(expected = InsufficientBalanceException.class)
    public void testUnauthorisedWithdrawal() {


        String accountId = "20-111-3333";
        BigDecimal initialBlance = new BigDecimal(3);
        BigDecimal withdrawaAmount = new BigDecimal(5);

        Account expectedAccount = new Account("firstname", "lastname");
        expectedAccount.setId(accountId);
        expectedAccount.setBalance(initialBlance);


        when(accountRepository.getById(accountId)).thenReturn(expectedAccount);

        accountService.withdraw(accountId, withdrawaAmount);

    }

    @Test
    public void testGetTransactions() {
        String accountId = "20-111-3333";
        TransactionType expectedTransactionType = TransactionType.DEPOSIT;
        BigDecimal initialBlance = new BigDecimal(0);
        BigDecimal transactionAmount = new BigDecimal(5);
        Account expectedAccount = new Account("firstname", "lastname");
        expectedAccount.setId(accountId);
        expectedAccount.setBalance(initialBlance);

        expectedAccount.getTransactionList().add(new Transaction(expectedTransactionType, transactionAmount, initialBlance));

        when(accountRepository.getById(accountId)).thenReturn(expectedAccount);

        List<Transaction> transactions = accountService.getTransactions(accountId);

        assertEquals(transactions.size(), 1);
        Transaction depositTransaction = transactions.get(0);
        assertEquals(depositTransaction.getType(), expectedTransactionType);
        assertEquals(depositTransaction.getAmount().intValue(), transactionAmount.intValue(), 0);
        assertEquals(depositTransaction.getInitialBalance().intValue(), initialBlance.intValue(), 0);


    }


}
