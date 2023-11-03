package com.example.bankexample.dto;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ErrorDataResponse {
    HttpStatus status;
    String exceptionMessage;
    String stackTrace;

    public ErrorDataResponse(HttpStatus status, String exceptionMessage, String stackTrace) {
        this.status = status;
        this.exceptionMessage = exceptionMessage;
        this.stackTrace = stackTrace;
    }
}
