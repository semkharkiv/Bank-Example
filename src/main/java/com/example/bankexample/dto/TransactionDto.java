package com.example.bankexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private String type;
    private String amount;
    private String description;
    private String debitAccount;
    private String creditAccount;
}
