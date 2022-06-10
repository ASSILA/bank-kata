package com.softeam.repository;

import com.softeam.model.Account;

public interface AccountRepository {

    String create(String firstName, String lastName);

    void save(Account account);

    Account getById(String id);
}
