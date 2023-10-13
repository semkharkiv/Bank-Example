package com.example.bankexample.controller;

import com.example.bankexample.dto.TransactionDto;
import com.example.bankexample.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/create")
    public TransactionDto createNewTransaction(@RequestBody TransactionDto transactionDto){
        return transactionService.createNewTransaction(transactionDto);
    }
}
