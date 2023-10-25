package com.example.bankexample.controller;

import com.example.bankexample.dto.ManagerDto;
import com.example.bankexample.entity.Manager;
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
class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllManagers() throws Exception{
        List<ManagerDto> managerDtoList = new ArrayList<>();
        ManagerDto firsManager = CreatorDto.getFirstManagerDto();
        ManagerDto secondManager = CreatorDto.getSecondManagerDto();
        managerDtoList.add(secondManager);
        managerDtoList.add(firsManager);

        MvcResult newManagerDtoListInDataBase = mockMvc.perform(get("/auth/managers/all"))
                .andExpect(status().isOk())
                .andReturn();

        String newManagerDtoListJson = newManagerDtoListInDataBase.getResponse().getContentAsString();

        List<ManagerDto> newManagerDtos = objectMapper.readValue(newManagerDtoListJson, new TypeReference<>() {
        } );

        Assertions.assertEquals(managerDtoList,newManagerDtos);
    }

    @Test
    void getManagerWithName() throws Exception{
        ManagerDto managerWithNameJohn = CreatorDto.getSecondManagerDto();
        String name = "John";

        MvcResult newManagerInDataBase = mockMvc.perform(get("/auth/managers/" + name))
                .andExpect(status().isOk())
                .andReturn();

        String managerDtoJson = newManagerInDataBase.getResponse().getContentAsString();
        ManagerDto newManagerDto = objectMapper.readValue(managerDtoJson, new TypeReference<>() {});

        Assertions.assertEquals(managerWithNameJohn,newManagerDto);
    }
}