package com.example.bankexample.service;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountById(Long id);

    AccountDto createAccount(AccountDto accountDto);

    List<Account> getAllAccounts();

    void deleteAccountById(Long id);

    Account changeAccountById(Long id, AccountDto accountDto);
}
