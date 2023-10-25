package com.example.bankexample.controller;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.util.CreatorDto;
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
import org.springframework.util.Assert;

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

        Assertions.assertEquals(agreementDto, newAgreementWithIdOne);
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
        AgreementDto agreementDto = CreatorDto.getAgreementDto();
        String jsonRequest = objectMapper.writeValueAsString(agreementDto);

        mockMvc.perform(post("/auth/agreements")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}