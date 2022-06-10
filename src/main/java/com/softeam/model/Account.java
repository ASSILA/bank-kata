package com.softeam.model;

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

}
