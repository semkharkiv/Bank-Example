package com.example.bankexample.service;

import com.example.bankexample.dto.AgreementDto;

import java.util.Optional;

public interface AgreementService {
    AgreementDto getAgreementDtoById(Long id);

    void deleteAgreementById(Long id);

    AgreementDto createAgreement(AgreementDto agreementDto);
}
