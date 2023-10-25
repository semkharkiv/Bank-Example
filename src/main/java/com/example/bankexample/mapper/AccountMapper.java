package com.example.bankexample.mapper;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.entity.Account;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Named("toDto")
    @Mapping(source = "client.firstName", target = "clientFirstName")
    @Mapping(source = "client.lastName", target = "clientLastName")
    @Mapping(source = "client.id", target = "id")
    AccountDto toDto(Account account);

    @Mapping(target = "accountType", source = "accountType", qualifiedByName = "stringToEnum")
    @Mapping(target = "currencyCode", source = "currencyCode", qualifiedByName = "stringToEnum")
    Account toEntity(AccountDto accountDto);

    @IterableMapping(qualifiedByName = "toDto")
    List<AccountDto> toDTOList(List<Account> accountList);

    @Named("stringToEnum")
    default String convertStringToEnum(String name) {
        return name.toUpperCase();
    }
}
