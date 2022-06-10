package com.softeam.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class Transaction {
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal initialBalance;
    private LocalDateTime creationDate;

    public Transaction(TransactionType type, BigDecimal amount, BigDecimal initialBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        // set creation date
        creationDate = LocalDateTime.now();
    }
}
