package com.example.bankexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private String name;
    private String accountType;
    private String accountStatus;
    private String balance;
    private String currencyCode;
    private String clientFirstName;
    private String clientLastName;
    private String agreementStatus;
}
