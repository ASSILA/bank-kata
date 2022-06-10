package com.softeam.service;

import com.softeam.model.Account;
import com.softeam.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    String createAccount(String firstName, String lastName);

    public Account getAccount(String accountId);

    void deposit(String accountId, BigDecimal amount);

    void withdraw(String accountId, BigDecimal amount);

    /**
     * get all transactions
     * @param accountId
     * @return
     */
    List<Transaction> getTransactions(String accountId);
}
