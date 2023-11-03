package com.example.bankexample.mapper;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.entity.Product;
import com.example.bankexample.repository.AccountRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "account.name", target = "accountName")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.interestRate", target = "interestRate")
    AgreementDto toDto(Agreement agreement);

    @Mapping(source = "agreementStatus", target = "agreementStatus", qualifiedByName = "stringToEnum")
    Agreement toEntity(AgreementDto agreementDto);

    @Named("stringToEnum")
    default String convertStringToEnum(String name) {
        return name.toUpperCase();
    }
}
