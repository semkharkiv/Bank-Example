package com.example.bankexample.service;

import com.example.bankexample.mapper.AgreementMapper;
import com.example.bankexample.repository.AccountRepository;
import com.example.bankexample.repository.AgreementRepository;
import com.example.bankexample.repository.ProductRepository;
import com.example.bankexample.exception.NotFoundException;
import com.example.bankexample.service.impl.AgreementServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotFoundExceptionTest {

    @Mock
    private AgreementRepository agreementRepository;
    @Mock
    private AgreementMapper agreementMapper;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private AgreementServiceImpl agreementService;

    @Test
    public void testGetAgreementDtoByIdWhenNotFound() {
        long agreementId = 5;

        when(agreementRepository.findById(agreementId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            agreementService.getAgreementDtoById(agreementId);
        });
    }
}
