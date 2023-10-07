package com.example.bankexample.dto;

import lombok.Data;


@Data
public class AccountDto {
    private String clientId;
    private String name;
    private String accountType;
    private String balance;
    private String currencyCode;
    private String clientFirstName;
    private String clientLastName;
}
