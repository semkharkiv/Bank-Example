package com.example.bankexample.service.impl;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.entity.Product;
import com.example.bankexample.exception.ErrorMessage;
import com.example.bankexample.exception.InvalidAgreementException;
import com.example.bankexample.exception.NotFoundException;
import com.example.bankexample.mapper.AgreementMapper;
import com.example.bankexample.repository.AccountRepository;
import com.example.bankexample.repository.AgreementRepository;
import com.example.bankexample.repository.ProductRepository;
import com.example.bankexample.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    /**
     * Получает {@link AgreementDto} по его идентификатору.
     *
     * @param id Идентификатор договора.
     * @return Соответствующий {@link AgreementDto}.
     * @throws NotFoundException Если договор не найден.
     */
    @Override
    @Transactional
    public AgreementDto getAgreementDtoById(Long id) {
        return agreementRepository.findById(id)
                .map(agreementMapper::toDto)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.AGREEMENT_NOT_FOUND_BY_ID));
    }

    /**
     * Удаляет договор по его идентификатору.
     *
     * @param id Идентификатор удаляемого договора.
     */
    @Override
    @Transactional
    public void deleteAgreementById(Long id) {
        agreementRepository.deleteById(id);
    }

    /**
     * Создает новый договор на основе предоставленного {@link AgreementDto}.
     *
     * @param agreementDto {@link AgreementDto} с информацией о договоре.
     * @return Созданный {@link AgreementDto}.
     * @throws NotFoundException          Если связанный аккаунт или продукт не найден.
     * @throws InvalidAgreementException Если тип аккаунта не соответствует типу продукта.
     */
    @Override
    @Transactional
    public AgreementDto createAgreement(AgreementDto agreementDto) {
        Agreement agreement = agreementMapper.toEntity(agreementDto);
        Account account = accountRepository.findById(Long.parseLong(agreementDto.getAccountId()))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_BY_ID));
        agreement.setAccount(account);
        Product product = productRepository.findById(Long.parseLong(agreementDto.getProductId()))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND_BY_ID));
        agreement.setProduct(product);
        if (!account.getAccountType().toString().equals(product.getName().toUpperCase())) {
            throw new InvalidAgreementException(ErrorMessage.WRONG_DATA);
        }
        agreementRepository.save(agreement);
        return agreementMapper.toDto(agreement);
    }
}
