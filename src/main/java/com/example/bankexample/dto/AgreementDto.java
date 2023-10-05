package com.example.bankexample.dto;

import com.example.bankexample.entity.enums.AgreementStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgreementDto {
    private String accountName;
    private String productName;
    private BigDecimal interestRate;
    private BigDecimal total;
    private AgreementStatus agreementStatus;

}
