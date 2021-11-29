package com.clint.marvel.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseServiceException extends RuntimeException{

    private final String code;
    private final String status;
    private final HttpStatus httpStatus;

    public BaseServiceException(String code, String status, HttpStatus httpStatus){
        this.code = code;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public BaseServiceException(ErrorCodes errorCodes){
        this.code = errorCodes.getCode();
        this.status = errorCodes.getMessage();
        this.httpStatus = errorCodes.getHttpStatus();
    }

    public BaseServiceException(BaseServiceException e, HttpStatus httpStatus){
        this.code = e.getCode();
        this.status = e.getStatus();
        this.httpStatus = httpStatus;
    }

}
