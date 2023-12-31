package com.example.bankexample.controller;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.entity.Client;
import com.example.bankexample.entity.Product;
import com.example.bankexample.entity.enums.AccountStatus;
import com.example.bankexample.entity.enums.AccountType;
import com.example.bankexample.repository.AgreementRepository;
import com.example.bankexample.util.CreatorDto;
import com.example.bankexample.util.CreatorEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Sql("/database/schema-cleanup.sql")
@Sql("/database/create_tables_test.sql")
@Sql("/database/insert_data_test.sql")
class AgreementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    ObjectMapper objectMapper;

    private long agreementId;

    @Test
    void getAgreementDtoByIdTest() throws Exception {
        AgreementDto agreementDto = CreatorDto.getAgreementDto();
        agreementId = 1;

        MvcResult responseAgreement = mockMvc.perform(get("/auth/agreements/" + agreementId))
                .andExpect(status().isOk())
                .andReturn();

        String agreementWithIdOneJson = responseAgreement.getResponse().getContentAsString();
        AgreementDto newAgreementWithIdOne = objectMapper.readValue(agreementWithIdOneJson, new TypeReference<>() {
        });

        assertEquals(agreementDto, newAgreementWithIdOne);
    }

    @Test
    void deleteAgreementByIdTest() throws Exception {
        agreementId = 1;

        mockMvc.perform(get("/auth/agreements/" + agreementId))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/auth/agreements/" + agreementId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/auth/agreements/" + agreementId))
                .andExpect(status().isNotFound());
    }

    @Test
    void createAgreementTest() throws Exception {
        Account account = CreatorEntity.getSecondAccount();
        Product product = CreatorEntity.getSecondProduct();

        AgreementDto agreementDto = new AgreementDto();
        agreementDto.setTotal("10000");
        agreementDto.setAccountId(account.getId().toString());
        agreementDto.setProductId(product.getId().toString());


        String agreementJson = objectMapper.writeValueAsString(agreementDto);

        mockMvc.perform(post("/auth/agreements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agreementJson))
                .andExpect(status().isCreated())
                .andReturn();

        List<Agreement> agreements = agreementRepository.findAll();
        assertEquals(2, agreements.size());

        Agreement createdAgreement = agreements.get(1);
        assertNotNull(createdAgreement.getId());
        assertEquals(new BigDecimal("10000.00"), createdAgreement.getTotal());
        assertEquals(account.getId(), createdAgreement.getAccount().getId());
        assertEquals(product.getId(), createdAgreement.getProduct().getId());
    }
}