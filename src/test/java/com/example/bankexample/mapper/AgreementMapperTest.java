package com.example.bankexample.mapper;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.dto.TransactionDto;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.entity.Transaction;
import com.example.bankexample.util.CreatorDto;
import com.example.bankexample.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AgreementMapperTest {

    @Autowired
    AgreementMapper agreementMapper;
    @Test
    void convertStringToEnum() {
        String result = agreementMapper.convertStringToEnum("test");

        assertEquals("TEST",result);
    }
}