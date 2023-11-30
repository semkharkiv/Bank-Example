package com.example.bankexample.controller;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/accounts")
@RequiredArgsConstructor
public class AccountController {

    public final AccountService accountService;

    /**
     * Обработчик HTTP GET запроса для получения информации о счете по его идентификатору.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(accountService.getAccountDtoById(id), HttpStatus.OK);
    }

    /**
     * Обработчик HTTP POST запроса для создания нового счета.
     */
    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    /**
     * Обработчик HTTP GET запроса для получения информации обо всех счетах.
     */
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccountDtos(), HttpStatus.OK);
    }

    /**
     * Обработчик HTTP DELETE запроса для удаления счета по его идентификатору.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable("id") Long id) {
        accountService.deleteAccountById(id);
    }
}
