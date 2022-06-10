package com.softeam.repository;

import com.softeam.model.Account;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountRepositoryTest {

    Map<String, Account> mockAccountList = mock(Map.class);
    private AccountRepository accountRepository;

    @Test
    public void testCreate() {

        String acountId = accountRepository.create("firstname", "lastname");
        assertNotNull(acountId);

    }

    @Test()
    public void testSaveThrowRepositoryException() {
        Account account = new Account("firstname", "lastname");
        account.setId(null);
        accountRepository.save(account);
    }

    @Test
    public void testGetById() {

        String accountId = "20";

        Account expectedAccount = new Account("firstname", "lastname");
        expectedAccount.setId(accountId);

        when(mockAccountList.get(accountId)).thenReturn(expectedAccount);

        Account account = accountRepository.getById(accountId);

        assertEquals(account, expectedAccount);

    }
}
