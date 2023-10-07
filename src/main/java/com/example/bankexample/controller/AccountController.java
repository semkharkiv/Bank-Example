package com.example.bankexample.controller;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/accounts")
@RequiredArgsConstructor
public class AccountController {

    public final AccountService accountService;

    @GetMapping("/{id}")
    public Optional<AccountDto> getAccountById(@PathVariable("id") Long id) {
        return accountService.getAccountDtoById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @GetMapping
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccountDtos();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable("id") Long id) {
        accountService.deleteAccountById(id);
    }
}
