package com.example.bankexample.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String type;
    private String amount;
    private String description;
    private String debitAccount;
    private String creditAccount;
}
