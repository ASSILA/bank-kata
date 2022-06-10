package com.softeam.repository;


import com.softeam.model.Account;

import java.util.Map;
import java.util.UUID;

public class AccountRepositoryImp implements AccountRepository {

    private Map<String, Account> dummyAccountList;

    public AccountRepositoryImp(Map<String, Account> dummyAccountList) {
        this.dummyAccountList = dummyAccountList;
    }

    @Override
    public String create(String firstName, String lastName) {
       return null;
    }

    @Override
    public void save(Account account) {
        // to save or update an account
    }
    @Override
    public Account getById(String id) {
        return null;
    }
}
