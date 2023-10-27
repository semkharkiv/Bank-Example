package com.example.bankexample.service.impl;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.entity.Product;
import com.example.bankexample.entity.enums.AgreementStatus;
import com.example.bankexample.mapper.AgreementMapper;
import com.example.bankexample.repository.AccountRepository;
import com.example.bankexample.repository.AgreementRepository;
import com.example.bankexample.repository.ProductRepository;
import com.example.bankexample.service.AgreementService;
import com.example.bankexample.service.exception.ErrorMessage;
import com.example.bankexample.service.exception.InvalidAgreementException;
import com.example.bankexample.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public AgreementDto getAgreementDtoById(Long id) {
        return agreementRepository.findById(id)
                .map(agreementMapper::toDto)
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
        agreement.setTotal(new BigDecimal(agreementDto.getTotal()));
        agreement.setCreatedAt(LocalDateTime.now());
        agreement.setUpdatedAt(LocalDateTime.now());
        Account account = accountRepository.findById(Long.parseLong(agreementDto.getAccountId()))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_BY_ID));
        agreement.setAccount(account);
        Product product = productRepository.findById(Long.parseLong(agreementDto.getProductId()))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND_BY_ID));
        agreement.setProduct(product);
        agreement.setAgreementStatus(AgreementStatus.ACTIVE);
        if (!account.getAccountType().toString().equals(product.getName().toUpperCase())) {
            throw new InvalidAgreementException(ErrorMessage.WRONG_DATA);
        }
        agreementRepository.save(agreement);
        return agreementMapper.toDto(agreement);
    }
}
