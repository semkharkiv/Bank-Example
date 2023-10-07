package com.example.bankexample.service;

import com.example.bankexample.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<AccountDto> getAccountDtoById(Long id);

    AccountDto createAccount(AccountDto accountDto);

    List<AccountDto> getAllAccountDtos();

    void deleteAccountById(Long id);
}
