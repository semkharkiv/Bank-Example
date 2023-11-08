package com.example.bankexample.service.impl;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.mapper.AccountMapper;
import com.example.bankexample.repository.AccountRepository;
import com.example.bankexample.repository.ClientRepository;
import com.example.bankexample.service.AccountService;
import com.example.bankexample.exception.ErrorMessage;
import com.example.bankexample.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public AccountDto getAccountDtoById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_BY_ID));
    }

    @Override
    @Transactional
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account.setName(generateAccountNumber());
        account.setClient(clientRepository.findById(Long.parseLong(accountDto.getId()))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.CLIENT_NOT_FOUND_BY_ID)));
        accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    @Override
    @Transactional
    public List<AccountDto> getAllAccountDtos() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.toDTOList(accounts);
    }

    @Override
    @Transactional
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public String generateAccountNumber() {
        String accountNumber;
        do {
            accountNumber = RandomStringUtils.randomNumeric(16);
        } while (accountRepository.existsAccountByName(accountNumber));
        return accountNumber;
    }
}
