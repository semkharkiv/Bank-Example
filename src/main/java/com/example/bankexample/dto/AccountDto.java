package com.example.bankexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String id;
    private String name;
    private String accountType;
    private String balance;
    private String currencyCode;
    private String clientFirstName;
    private String clientLastName;
}
