package com.example.bankexample.service.impl;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.enums.AccountStatus;
import com.example.bankexample.mapper.AccountMapper;
import com.example.bankexample.repository.AccountRepository;
import com.example.bankexample.repository.ClientRepository;
import com.example.bankexample.service.AccountService;
import com.example.bankexample.service.exception.NotFoundException;
import com.example.bankexample.service.exception.ErrorMessage;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientRepository clientRepository;
    private final Set<String> generatedAccountNumbers = new HashSet<>();

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
        account.setAccountStatus(AccountStatus.NEW);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setClient(clientRepository.findById(Long.parseLong(accountDto.getClientId()))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.CLIENT_NOT_FOUND_BY_ID)));
        account.setName(generateAccountNumber());
        account.setBalance(new BigDecimal("0.0"));
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

    private String generateAccountNumber() {
        String accountNumber;
        do {
            accountNumber = RandomStringUtils.randomNumeric(16);
        } while (generatedAccountNumbers.contains(accountNumber));
        generatedAccountNumbers.add(accountNumber);
        return accountNumber;
    }
}
