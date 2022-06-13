package com.softeam.model;

import com.softeam.exception.InsufficientBalanceException;
import com.softeam.exception.UnsupportedTransactionTypeException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Account {
    private String id;
    private String firstName;
    private String lastName;
    private BigDecimal balance;
    List<Transaction> transactionList = new ArrayList<>();

    public Account(String firstName, String lastName) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = new BigDecimal(0);
        this.transactionList = new ArrayList<>();
    }

    public void performTransaction(BigDecimal transactionAmount, TransactionType transactionType) {
        BigDecimal initialBalance = this.getBalance();
        Transaction transaction = new Transaction(transactionType, transactionAmount, initialBalance);

        // add transaction to account transaction list
        this.getTransactionList().add(transaction);

        // calculate and set new balance
        BigDecimal newBalance = calculateNewBalance(initialBalance, transactionAmount, transactionType);
        this.setBalance(newBalance);
    }

    public BigDecimal calculateNewBalance(BigDecimal initialBalance, BigDecimal transactionAmount, TransactionType transactionType) {
        if (TransactionType.DEPOSIT.equals(transactionType)) {
            return initialBalance.add(transactionAmount);
        } else if (TransactionType.WITHDRAWAL.equals(transactionType)) {
            if (initialBalance.compareTo(transactionAmount) < 0) {
                throw new InsufficientBalanceException("WITHDRAWAL not authorised because of insufficient balance");
            }
            return initialBalance.subtract(transactionAmount);
        }
        throw new UnsupportedTransactionTypeException("Unsupported transaction type");
    }

}
