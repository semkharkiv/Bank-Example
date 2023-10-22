package com.example.bankexample.service.exception;

public class InvalidAgreementException extends RuntimeException{
    public InvalidAgreementException(String message) {
        super(message);
    }
}
