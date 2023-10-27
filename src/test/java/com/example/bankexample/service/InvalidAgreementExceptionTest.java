package com.example.bankexample.service;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.Product;
import com.example.bankexample.service.exception.InvalidAgreementException;
import com.example.bankexample.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class InvalidAgreementExceptionTest {

    @Autowired
    private AgreementService agreementService;

    @Test
    void createAgreement_ThrowsInvalidAgreementException() {
        AgreementDto agreementDto = new AgreementDto();
        Account account = CreatorEntity.getFirsAccount();
        Product product = CreatorEntity.getSecondProduct();
        agreementDto.setAccountId(String.valueOf(account.getId()));
        agreementDto.setProductId(String.valueOf(product.getId()));
        agreementDto.setTotal("10000.00");

        assertThrows(InvalidAgreementException.class, () -> {
            agreementService.createAgreement(agreementDto);
        });
    }
}
