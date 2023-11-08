package com.example.bankexample.mapper;

import com.example.bankexample.dto.TransactionDto;
import com.example.bankexample.entity.Account;
import com.example.bankexample.entity.Agreement;
import com.example.bankexample.entity.Transaction;
import com.example.bankexample.entity.enums.TransactionType;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Named("toTransactionDto")
    @Mapping(target = "debitAccount", source = "transaction.debitAccount.id")
    @Mapping(target = "creditAccount", source = "transaction.creditAccount.id")
    TransactionDto toDto(Transaction transaction);

    @IterableMapping(qualifiedByName = "toTransactionDto")
    List<TransactionDto> toListDto(List<Transaction> transactionList);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "debitAccount", source = "debitAccount", qualifiedByName = "convertToAccount")
    @Mapping(target = "creditAccount", source = "creditAccount", qualifiedByName = "convertToAccount")
    Transaction toEntity(TransactionDto transactionDto);

    @AfterMapping
    default void setDefaults(@MappingTarget Transaction transaction) {
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setType(TransactionType.APPROVED);
    }

    @Named("convertToAccount")
    default Account convertToAccount(String accountId) {
        Account account = new Account();
        account.setId(Long.valueOf(accountId));
        return account;
    }
}
