package com.example.bankexample.model.enums;

public enum CurrencyType {
    EUR("EUR"),
    USD("USD"),
    UAH("UAH");
    private final String value;

    CurrencyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
