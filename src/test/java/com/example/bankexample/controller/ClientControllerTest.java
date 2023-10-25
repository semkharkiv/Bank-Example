package com.example.bankexample.controller;

import com.example.bankexample.dto.ClientDto;
import com.example.bankexample.util.CreatorDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Sql("/database/schema-cleanup.sql")
@Sql("/database/create_tables_test.sql")
@Sql("/database/insert_data_test.sql")
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllActiveClientDto() throws Exception {
        List<ClientDto> clientDtoList = new ArrayList<>();
        ClientDto firsClientDto = CreatorDto.getFirstClientDto();
        ClientDto secondClientDto = CreatorDto.getSecondClientDto();
        clientDtoList.add(firsClientDto);
        clientDtoList.add(secondClientDto);

        MvcResult responseClientDtoList = mockMvc.perform(get("/auth/clients/all-active"))
                .andExpect(status().isOk())
                .andReturn();

        String newClientDtoListJson = responseClientDtoList.getResponse().getContentAsString();
        List<ClientDto> newClientDtos = objectMapper.readValue(newClientDtoListJson, new TypeReference<>() {
        });

        Assertions.assertEquals(clientDtoList,newClientDtos);
    }

    @Test
    void getAllClientsByStatus() throws Exception {
        List<ClientDto> clientDtoList = new ArrayList<>();
        ClientDto thirdClientDto = CreatorDto.getThirdClientDto();
        clientDtoList.add(thirdClientDto);

        String status = "BLOCKED";

        MvcResult responseClientDtoList = mockMvc.perform(get("/auth/clients/" + status))
                .andExpect(status().isOk())
                .andReturn();

        String newClientDtoListJson = responseClientDtoList.getResponse().getContentAsString();
        List<ClientDto> newClientDtos = objectMapper.readValue(newClientDtoListJson, new TypeReference<>() {
        });

        Assertions.assertEquals(clientDtoList,newClientDtos);
    }
}