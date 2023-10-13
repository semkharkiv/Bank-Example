package com.example.bankexample.service;

import com.example.bankexample.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProductWithManagerName(String name);
}
