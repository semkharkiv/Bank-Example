package com.example.bankexample.exception;

public class InvalidAgreementException extends RuntimeException {
    public InvalidAgreementException(String message) {
        super(message);
    }
}
