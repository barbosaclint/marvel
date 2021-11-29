package com.clint.marvel.exception;

import jdk.jshell.spi.SPIResolutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serializable;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { BaseServiceException.class })
    public ResponseEntity<Serializable> handleApiRequestException(ErrorCodes errorCodes) {
        BaseServiceException baseException = new BaseServiceException(
                errorCodes.getCode(),
                errorCodes.getMessage(),
                errorCodes.getHttpStatus()
        );
        return new ResponseEntity<>(baseException, errorCodes.getHttpStatus());
    }
}
