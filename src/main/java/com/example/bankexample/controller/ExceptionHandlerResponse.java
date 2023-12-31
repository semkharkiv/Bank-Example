package com.example.bankexample.controller;

import com.example.bankexample.dto.ErrorDataResponse;
import com.example.bankexample.exception.InvalidAgreementException;
import com.example.bankexample.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ExceptionHandlerResponse {

    /**
     * Обработчик исключения NotFoundException.
     * @param exception Исключение NotFoundException.
     * @return Объект ResponseEntity с данными об ошибке и статусом NOT_FOUND.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDataResponse> handlerNotFoundException(NotFoundException exception) {
        ErrorDataResponse errorResponse = new ErrorDataResponse(HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()),
                exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Обработчик исключения InvalidAgreementException.
     * @param exception Исключение InvalidAgreementException.
     * @return Объект ResponseEntity с данными об ошибке и статусом BAD_REQUEST.
     */
    @ExceptionHandler(InvalidAgreementException.class)
    public ResponseEntity<ErrorDataResponse> handlerInvalidAgreementException(InvalidAgreementException exception) {
        ErrorDataResponse errorResponse = new ErrorDataResponse(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()),
                exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик исключения RuntimeException.
     * @param exception Исключение RuntimeException.
     * @return Объект ResponseEntity с данными об ошибке и статусом INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDataResponse> handlerRuntimeException(RuntimeException exception) {
        ErrorDataResponse errorResponse = new ErrorDataResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
