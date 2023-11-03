package com.example.bankexample.service;

import com.example.bankexample.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto getAccountDtoById(Long id);

    AccountDto createAccount(AccountDto accountDto);

    List<AccountDto> getAllAccountDtos();

    void deleteAccountById(Long id);
}
