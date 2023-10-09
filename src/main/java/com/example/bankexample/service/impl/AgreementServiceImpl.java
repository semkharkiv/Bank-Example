package com.example.bankexample.service.impl;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.mapper.AgreementMapper;
import com.example.bankexample.repository.AccountRepository;
import com.example.bankexample.repository.AgreementRepository;
import com.example.bankexample.repository.ProductRepository;
import com.example.bankexample.service.AgreementService;
import com.example.bankexample.service.exception.ErrorMessage;
import com.example.bankexample.service.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

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
    @Transactional
    public AgreementDto createAgreement(AgreementDto agreementDto) {
        Agreement agreement = agreementMapper.toEntity(agreementDto);
        agreement.setCreatedAt(LocalDateTime.now());
        agreement.setUpdatedAt(LocalDateTime.now());
        agreement.setAccount(accountRepository.findById(Long.parseLong(agreementDto.getAccountId()))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_BY_ID)));
        agreement.setProduct(productRepository.findById(Long.parseLong(agreementDto.getProductId()))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND_BY_ID)));
        agreementRepository.save(agreement);
        return agreementMapper.toDto(agreement);
    }


}
