package com.softeam.service;


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
    }

    @Override
    public void withdraw(String accountId, BigDecimal amount) {
        // Withdraw from Account
    }

    @Override
    public List<Transaction> getTransactions(String accountId) {
        Account account = accountRepository.getById(accountId);
        return account.getTransactionList();
    }



}
