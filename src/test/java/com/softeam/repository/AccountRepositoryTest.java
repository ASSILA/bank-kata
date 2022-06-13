package com.softeam.repository;

import com.softeam.exception.RepositoryException;
import com.softeam.model.Account;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class AccountRepositoryTest {

    Map<String, Account> mockAccountList = mock(Map.class);
    private AccountRepository accountRepository = new AccountRepositoryImp(mockAccountList);

    @Test
    public void testCreate() {

        String acountId = accountRepository.create("firstname", "lastname");
        assertNotNull(acountId);

    }

    @Test(expected = RepositoryException.class)
    public void testSaveThrowRepositoryException() {
        Account account = new Account("firstname", "lastname");
        account.setId(null);
        accountRepository.save(account);
    }
}
