package com.example.bankexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgreementDto {
    private String accountName;
    private String productName;
    private String interestRate;
    private String total;
    private String agreementStatus;

}
