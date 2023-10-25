package com.example.bankexample.util;

import com.example.bankexample.entity.*;
import com.example.bankexample.entity.enums.*;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class CreatorEntity {

    public static Manager getFirstManager() {
        Manager manager = new Manager();
        manager.setId(1L);
        manager.setFirstName("Mickael");
        manager.setLastName("Jonson");
        manager.setManagerStatus(ManagerStatus.ACTIVE);
        return manager;
    }

    public static Manager getSecondManager() {
        Manager manager = new Manager();
        manager.setId(2L);
        manager.setFirstName("John");
        manager.setLastName("Smith");
        manager.setManagerStatus(ManagerStatus.ACTIVE);
        return manager;
    }

    public static Client getFirstClient() {
        Client client = new Client();
        client.setId(1L);
        client.setClientStatus(ClientStatus.ACTIVE);
        client.setTaxCode("222606111");
        client.setFirstName("Amanda");
        client.setLastName("Spears");
        client.setEmail("Amspr@gmail.com");
        client.setAddress("Germany,Essen, str.Brunenstrasse, 1");
        client.setPhone("+49-175-660-21-13");
        client.setManager(getFirstManager());
        return client;
    }

    public static Client getSecondClient() {
        Client client = new Client();
        client.setId(2L);
        client.setClientStatus(ClientStatus.ACTIVE);
        client.setTaxCode("222606122");
        client.setFirstName("Shon");
        client.setLastName("Robinson");
        client.setEmail("Robinshon@gmail.com");
        client.setAddress("Germany,Dusseldorf, str.Kreuzerstrasse, 12");
        client.setPhone("+49-175-967-61-15");
        client.setManager(getSecondManager());
        return client;
    }

    public static Client getThirdClient() {
        Client client = new Client();
        client.setId(3L);
        client.setClientStatus(ClientStatus.BLOCKED);
        client.setTaxCode("222606123");
        client.setFirstName("Ron");
        client.setLastName("Davidson");
        client.setEmail("Davidson@gmail.com");
        client.setAddress("Germany,Dusseldorf, str.Kreuzerstrasse, 15");
        client.setPhone("+49-175-967-14-15");
        client.setManager(getSecondManager());
        return client;
    }

    public static Account getFirsAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setName("4444566543212345");
        account.setAccountType(AccountType.DEPOSIT);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCurrencyCode(CurrencyCode.EUR);
        account.setBalance(new BigDecimal("20000.00"));
        account.setClient(getFirstClient());
        return account;
    }

    public static Account getSecondAccount() {
        Account account = new Account();
        account.setId(2L);
        account.setName("4444566543212356");
        account.setAccountType(AccountType.CREDIT);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCurrencyCode(CurrencyCode.EUR);
        account.setBalance(new BigDecimal("50000.00"));
        account.setClient(getSecondClient());
        return account;
    }

    public static Product getFirstProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("deposit");
        product.setAccountStatus(ProductStatus.ACTIVE);
        product.setCurrencyCode("EUR");
        product.setInterestRate(new BigDecimal("10.4000"));
        product.setLimitAmount(3000000);
        product.setManager(getFirstManager());
        return product;
    }

    public static Product getSecondProduct() {
        Product product = new Product();
        product.setId(2L);
        product.setName("credit");
        product.setAccountStatus(ProductStatus.ACTIVE);
        product.setCurrencyCode("EUR");
        product.setInterestRate(new BigDecimal("9.5000"));
        product.setLimitAmount(4000000);
        product.setManager(getFirstManager());
        return product;
    }

    public static Agreement getAgreement() {
        Agreement agreement = new Agreement();
        agreement.setId(1L);
        agreement.setAgreementStatus(AgreementStatus.ACTIVE);
        agreement.setTotal(new BigDecimal("15000.00"));
        agreement.setAccount(getFirsAccount());
        agreement.setProduct(getFirstProduct());
        return agreement;
    }

    public static Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setType(TransactionType.APPROVED);
        transaction.setAmount(new BigDecimal("2000.00"));
        transaction.setDescription("Payment by agreement 01 from 02.04.23");
        transaction.setCreditAccount(getFirsAccount());
        transaction.setDebitAccount(getSecondAccount());
        return transaction;
    }
}
