package com.example.bankexample.entity.enums;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    USD("US Dollar"),
    EUR("Euro"),
    GBP("Pound Sterling"),
    UAH("Hryvnia"),
    PLN("Zloty"),
    CZK("Czech Koruna"),
    TRY("Turkish Lira");


    private final String currencyName;

    CurrencyCode(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
