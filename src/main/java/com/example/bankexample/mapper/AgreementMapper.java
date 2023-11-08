package com.example.bankexample.mapper;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.entity.Product;
import com.example.bankexample.entity.enums.AccountStatus;
import com.example.bankexample.entity.enums.AgreementStatus;
import com.example.bankexample.repository.AccountRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "account.name", target = "accountName")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.interestRate", target = "interestRate")
    AgreementDto toDto(Agreement agreement);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "agreementStatus", ignore = true)
    Agreement toEntity(AgreementDto agreementDto);

    @AfterMapping
    default void setDefaults(@MappingTarget Agreement agreement) {
        agreement.setCreatedAt(LocalDateTime.now());
        agreement.setUpdatedAt(LocalDateTime.now());
        agreement.setAgreementStatus(AgreementStatus.ACTIVE);
    }

    @Named("stringToEnum")
    default String convertStringToEnum(String name) {
        return name.toUpperCase();
    }
}
