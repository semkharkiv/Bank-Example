package com.example.bankexample.service.impl;

import com.example.bankexample.dto.TransactionDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.Transaction;
import com.example.bankexample.mapper.TransactionMapper;
import com.example.bankexample.repository.TransactionRepository;
import com.example.bankexample.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public TransactionDto createNewTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }
}
