package com.example.bankexample.service;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Agreement;

public interface AgreementService {
    Agreement getAgreementById(Long id);

    void deleteAgreementById(Long id);

    AgreementDto createAgreement(AgreementDto agreementDto);
}
