package com.example.bankexample.util;

import com.example.bankexample.dto.*;
import com.example.bankexample.entity.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreatorDto {

    public static ManagerDto getFirstManagerDto() {
        Manager manager = CreatorEntity.getFirstManager();
        return new ManagerDto(
                manager.getFirstName(),
                manager.getLastName(),
                String.valueOf(manager.getManagerStatus())
        );
    }

    public static ManagerDto getSecondManagerDto() {
        Manager manager = CreatorEntity.getSecondManager();
        return new ManagerDto(
                manager.getFirstName(),
                manager.getLastName(),
                String.valueOf(manager.getManagerStatus())
        );
    }

    public static ClientDto getFirstClientDto() {
        Client client = CreatorEntity.getFirstClient();
        return new ClientDto(
                client.getId().toString(),
                String.valueOf(client.getClientStatus()),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail()
        );
    }

    public static ClientDto getSecondClientDto() {
        Client client = CreatorEntity.getSecondClient();
        return new ClientDto(
                client.getId().toString(),
                String.valueOf(client.getClientStatus()),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail()
        );
    }

    public static ClientDto getThirdClientDto() {
        Client client = CreatorEntity.getThirdClient();
        return new ClientDto(
                client.getId().toString(),
                String.valueOf(client.getClientStatus()),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail()
        );
    }

    public static AccountDto getFirstAccountDto() {
        Account account = CreatorEntity.getFirsAccount();
        return new AccountDto(
                getFirstClientDto().getId(),
                account.getName(),
                String.valueOf(account.getAccountType()),
                String.valueOf(account.getBalance()),
                String.valueOf(account.getCurrencyCode()),
                getFirstClientDto().getFirstName(),
                getFirstClientDto().getLastName()
        );
    }

    public static AccountDto getSecondAccountDto() {
        Account account = CreatorEntity.getSecondAccount();
        return new AccountDto(
                getSecondClientDto().getId(),
                account.getName(),
                String.valueOf(account.getAccountType()),
                String.valueOf(account.getBalance()),
                String.valueOf(account.getCurrencyCode()),
                getSecondClientDto().getFirstName(),
                getSecondClientDto().getLastName()
        );
    }

    public static ProductDto getFirstProductDto() {
        Product product = CreatorEntity.getFirstProduct();
        return new ProductDto(
                product.getId().toString(),
                product.getName(),
                String.valueOf(product.getAccountStatus()),
                product.getCurrencyCode(),
                product.getInterestRate().toString(),
                String.valueOf(product.getLimitAmount())
        );
    }

    public static ProductDto getSecondProductDto() {
        Product product = CreatorEntity.getSecondProduct();
        return new ProductDto(
                product.getId().toString(),
                product.getName(),
                String.valueOf(product.getAccountStatus()),
                product.getCurrencyCode(),
                product.getInterestRate().toString(),
                String.valueOf(product.getLimitAmount())
        );
    }

    public static AgreementDto getAgreementDto() {
        Agreement agreement = CreatorEntity.getAgreement();
        return new AgreementDto(
                getFirstAccountDto().getName(),
                getFirstProductDto().getName(),
                getFirstProductDto().getInterestRate(),
                agreement.getTotal().toString(),
                agreement.getAgreementStatus().toString(),
                getFirstAccountDto().getId(),
                getFirstProductDto().getId()
        );
    }

    public static TransactionDto getTransactionDto() {
        Transaction transaction = CreatorEntity.getTransaction();
        return new TransactionDto(
                transaction.getType().toString(),
                transaction.getAmount().toString(),
                transaction.getDescription(),
                transaction.getDebitAccount().getId().toString(),
                transaction.getCreditAccount().getId().toString()
        );
    }
}
