package com.example.bankexample.controller;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.entity.Account;
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
    public Optional<Account> getAccountById(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountDto createAccount(@RequestBody AccountDto accountDto){
        return accountService.createAccount(accountDto);
    }

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable("id") Long id){
        accountService.deleteAccountById(id);
    }

    @PatchMapping ("/{id}")
    public Account changeAccountById(@PathVariable("id") Long id,@RequestBody AccountDto accountDto){
        return accountService.changeAccountById(id,accountDto);
    }
}
