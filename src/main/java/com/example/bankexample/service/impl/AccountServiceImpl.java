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

    /**
     * Получает {@link AccountDto} по его идентификатору.
     *
     * @param id Идентификатор аккаунта.
     * @return Соответствующий {@link AccountDto}.
     * @throws NotFoundException Если аккаунт не найден.
     */
    @Override
    @Transactional
    public AccountDto getAccountDtoById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_BY_ID));
    }

    /**
     * Создает новый аккаунт на основе предоставленного {@link AccountDto}.
     *
     * @param accountDto {@link AccountDto} с информацией об аккаунте.
     * @return Созданный {@link AccountDto}.
     * @throws NotFoundException Если связанный клиент не найден.
     */
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

    /**
     * Получает список всех {@link AccountDto}.
     *
     * @return Список {@link AccountDto}.
     */
    @Override
    @Transactional
    public List<AccountDto> getAllAccountDtos() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.toDTOList(accounts);
    }

    /**
     * Удаляет аккаунт по его идентификатору.
     *
     * @param id Идентификатор удаляемого аккаунта.
     */
    @Override
    @Transactional
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    /**
     * Генерирует уникальный номер аккаунта, которого нет в репозитории.
     *
     * @return Уникальный номер аккаунта.
     */
    public String generateAccountNumber() {
        String accountNumber;
        do {
            accountNumber = RandomStringUtils.randomNumeric(16);
        } while (accountRepository.existsAccountByName(accountNumber));
        return accountNumber;
    }
}
