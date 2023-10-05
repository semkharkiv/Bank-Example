package com.example.bankexample.mapper;

import com.example.bankexample.dto.AccountDto;
import com.example.bankexample.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AccountMapper {
//    @Mapping(target = "clientFirstName", source = "client.firstName")
//    @Mapping(target = "clientLastName", source = "client.lastname")
//    @Mapping(target = "agreementStatus", source = "agreement.agreementStatus")
    AccountDto toDto(Account account);

    List<AccountDto> toDTOList(List<Account> accountList);

}
