package com.example.bankexample.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String accountStatus;
    private String currencyCode;
    private String interestRate;
    private String limitAmount;
}
