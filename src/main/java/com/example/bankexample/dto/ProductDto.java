package com.example.bankexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String accountStatus;
    private String currencyCode;
    private String interestRate;
    private String limitAmount;
}
