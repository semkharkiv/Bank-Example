package com.example.bankexample.service;

import com.example.bankexample.dto.TransactionDto;

public interface TransactionService {
    TransactionDto createNewTransaction(TransactionDto transactionDto);
}
