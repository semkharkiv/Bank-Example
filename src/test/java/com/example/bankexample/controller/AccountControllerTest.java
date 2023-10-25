package com.example.bankexample.controller;

import com.example.bankexample.dto.AccountDto;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Sql("/database/schema-cleanup.sql")
@Sql("/database/create_tables_test.sql")
@Sql("/database/insert_data_test.sql")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAccountByIdTest() throws Exception {
        AccountDto accountWithIdOne = CreatorDto.getFirstAccountDto();
        long accountId = 1;

        MvcResult responseAccount = mockMvc.perform(get("/auth/accounts/" + accountId))
                .andExpect(status().isOk())
                .andReturn();

        String accountWithIdOneJson = responseAccount.getResponse().getContentAsString();
        AccountDto newAccountWithIdOne = objectMapper.readValue(accountWithIdOneJson, new TypeReference<>() {
        });

        Assertions.assertEquals(accountWithIdOne, newAccountWithIdOne);
    }

    @Test
    void createAccountTest() throws Exception {
        AccountDto accountDto = CreatorDto.getFirstAccountDto();

        String jsonRequest = objectMapper.writeValueAsString(accountDto);

        mockMvc.perform(post("/auth/accounts/create")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllAccountsTest() throws Exception {
        List<AccountDto> accountDtoList = new ArrayList<>();
        AccountDto firstAccountDto = CreatorDto.getFirstAccountDto();
        AccountDto secondAccountDto = CreatorDto.getSecondAccountDto();
        accountDtoList.add(firstAccountDto);
        accountDtoList.add(secondAccountDto);

        MvcResult responseAccountDtoList = mockMvc.perform(get("/auth/accounts"))
                .andExpect(status().isOk())
                .andReturn();

        String newAccountDtoListJson = responseAccountDtoList.getResponse().getContentAsString();

        List<AccountDto> newAccountDtos = objectMapper.readValue(newAccountDtoListJson, new TypeReference<>() {
        });

        Assertions.assertEquals(accountDtoList, newAccountDtos);
    }

    @Test
    void deleteAccountByIdTest() throws Exception {
        long accountId = 2;

        mockMvc.perform(get("/auth/accounts/" + accountId))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/auth/accounts/" + accountId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/auth/accounts/" + accountId))
                .andExpect(status().isNotFound());
    }
}