package com.example.bankexample.service.impl;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.repository.AgreementRepository;
import com.example.bankexample.service.AgreementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    @Override
    public Agreement getAgreementById(Long id) {
        return agreementRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteAgreementById(Long id) {
        agreementRepository.deleteById(id);
    }

    //не работает модельМаппер
    @Override
    public AgreementDto createAgreement(AgreementDto agreementDto) {
        return null;
    }


}
