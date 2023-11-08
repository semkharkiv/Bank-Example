package com.example.bankexample.mapper;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.enums.AccountStatus;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Named("toDto")
    @Mapping(source = "client.firstName", target = "clientFirstName")
    @Mapping(source = "client.lastName", target = "clientLastName")
    @Mapping(source = "client.id", target = "id")
    AccountDto toDto(Account account);

    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(source = "accountType", target = "accountType", qualifiedByName = "stringToEnum")
    @Mapping(source = "currencyCode", target = "currencyCode", qualifiedByName = "stringToEnum")
    Account toEntity(AccountDto accountDto);

    @IterableMapping(qualifiedByName = "toDto")
    List<AccountDto> toDTOList(List<Account> accountList);

    @AfterMapping
    default void setDefaults(@MappingTarget Account account) {
        account.setBalance(BigDecimal.ZERO);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setAccountStatus(AccountStatus.ACTIVE);
    }

    @Named("stringToEnum")
    default String convertStringToEnum(String name) {
        return name.toUpperCase();
    }
}
