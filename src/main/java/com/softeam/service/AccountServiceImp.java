package com.softeam.service;


import com.softeam.exception.ServiceException;
import com.softeam.model.Account;
import com.softeam.model.Transaction;
import com.softeam.model.TransactionType;
import com.softeam.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

public class AccountServiceImp implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String createAccount(String firstName, String lastName) {
        return accountRepository.create(firstName, lastName);
    }

    @Override
    public Account getAccount(String accountId) {
        return accountRepository.getById(accountId);
    }

    @Override
    public void deposit(String accountId, BigDecimal amount) {
        // Deposit into Account
        performTransaction(accountId, amount, TransactionType.DEPOSIT);
    }

    @Override
    public void withdraw(String accountId, BigDecimal amount) {
        // Withdraw from Account
        performTransaction(accountId, amount, TransactionType.WITHDRAWAL);
    }

    @Override
    public List<Transaction> getTransactions(String accountId) {
        Account account = accountRepository.getById(accountId);
        return account.getTransactionList();
    }

    /**
     * perform transaction of type deposit and withdrawal
     *
     * @param accountId
     * @param transactionAmount
     * @param transactionType
     */
    private void performTransaction(String accountId, BigDecimal transactionAmount, TransactionType transactionType) {

        Account account = accountRepository.getById(accountId);
        if (account == null) {
            throw new ServiceException("Account not found");
        }

        BigDecimal initialBalance = account.getBalance();
        Transaction transaction = new Transaction(transactionType, transactionAmount, initialBalance);

        // add transaction to account transaction list
        account.getTransactionList().add(transaction);

        // calculate and set new balance
        BigDecimal newBalance = calculateNewBalance(initialBalance, transactionAmount, transactionType);
        account.setBalance(newBalance);

        // save account
        accountRepository.save(account);
    }

    private BigDecimal calculateNewBalance(BigDecimal initialBalance, BigDecimal transactionAmount, TransactionType transactionType) {
        if (TransactionType.DEPOSIT.equals(transactionType)) {
            return initialBalance.add(transactionAmount);
        } else if (TransactionType.WITHDRAWAL.equals(transactionType)) {
            if (initialBalance.compareTo(transactionAmount) < 0) {
                throw new ServiceException("WITHDRAWAL not authorised because of insufficient balance");
            }
            return initialBalance.subtract(transactionAmount);
        }
        throw new ServiceException("Unsupported transaction type");
    }


}
