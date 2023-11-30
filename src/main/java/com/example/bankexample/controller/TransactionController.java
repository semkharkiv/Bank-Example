package com.example.bankexample.controller;

import com.example.bankexample.dto.TransactionDto;
import com.example.bankexample.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    /**
     * Создать новую транзакцию.
     * @param transactionDto Объект с данными транзакции, переданный в теле запроса.
     * @return Объект ResponseEntity с созданной TransactionDto и статусом CREATED.
     */
    @PostMapping("/create")
    public ResponseEntity<TransactionDto> createNewTransaction(@RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.createNewTransaction(transactionDto), HttpStatus.CREATED);
    }
}
