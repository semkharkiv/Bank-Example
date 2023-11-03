package com.example.bankexample.mapper;

import com.example.bankexample.dto.TransactionDto;
import com.example.bankexample.entity.Transaction;
import com.example.bankexample.util.CreatorDto;
import com.example.bankexample.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TransactionMapperTest {

    TransactionMapper transactionMapper = new TransactionMapperImpl();

    @Test
    void toListDto() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(CreatorEntity.getTransaction());
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionDtoList.add(CreatorDto.getTransactionDto());

        List<TransactionDto> outputTransactionDtoList = transactionMapper.toListDto(transactionList);
        assertEquals(transactionDtoList, outputTransactionDtoList);
    }
}
