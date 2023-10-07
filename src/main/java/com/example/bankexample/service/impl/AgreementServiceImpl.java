package com.example.bankexample.service.impl;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.mapper.AgreementMapper;
import com.example.bankexample.repository.AgreementRepository;
import com.example.bankexample.service.AgreementService;
import com.example.bankexample.service.exception.ErrorMessage;
import com.example.bankexample.service.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    @Override
    @Transactional
    public Optional<AgreementDto> getAgreementDtoById(Long id) {
        Optional<Agreement> optionalAgreement = agreementRepository.findById(id);
        return optionalAgreement.map(agreement -> Optional.of(agreementMapper.toDto(agreement)))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.AGREEMENT_NOT_FOUND_BY_ID));
    }

    @Override
    @Transactional
    public void deleteAgreementById(Long id) {
        agreementRepository.deleteById(id);
    }

    @Override
    public AgreementDto createAgreement(AgreementDto agreementDto) {
        return null;
    }


}
