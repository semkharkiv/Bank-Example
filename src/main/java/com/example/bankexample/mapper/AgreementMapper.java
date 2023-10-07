package com.example.bankexample.mapper;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
    @Mapping(source = "account.name", target = "accountName")
    @Mapping(source = "product.name", target = "productName")
    AgreementDto toDto(Agreement agreement);

    @Mapping(source = "agreementStatus", target = "agreementStatus", qualifiedByName = "stringToEnum")
    Agreement toEntity (AgreementDto agreementDto);

    @Named("stringToEnum")
    default String convertStringToEnum(String name){return name.toUpperCase();}
}