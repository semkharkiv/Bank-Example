package com.example.bankexample.controller;

import com.example.bankexample.dto.ProductDto;
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
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getProductWithManagerNameTest() throws Exception {
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto firstProductDto = CreatorDto.getFirstProductDto();
        ProductDto secondProductDto = CreatorDto.getSecondProductDto();
        productDtoList.add(firstProductDto);
        productDtoList.add(secondProductDto);

        String nameOfProduct = "John";

        MvcResult responseProducts = mockMvc.perform(get("/auth/products/" + nameOfProduct))
                .andExpect(status().isOk())
                .andReturn();

        String responseProductsJson = responseProducts.getResponse().getContentAsString();
        List<ProductDto> productDtos = objectMapper.readValue(responseProductsJson, new TypeReference<>() {
        });

        Assertions.assertEquals(productDtoList,productDtos);
    }
}