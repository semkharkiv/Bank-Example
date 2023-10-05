package com.example.bankexample.service.impl;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.mapper.AccountMapper;
import com.example.bankexample.repository.AccountRepository;
import com.example.bankexample.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
//        Account account = accountMapper;

        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account changeAccountById(Long id, AccountDto accountDto) {

        return null;
    }


}
