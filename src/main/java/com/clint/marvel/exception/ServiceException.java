package com.clint.marvel.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends BaseServiceException{

    private static final long serialVersionUID = -2995950689024541484L;

    private final HttpStatus httpStatus;

    public ServiceException(String code, String msg, Object detail){
        super(code, msg, detail);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ServiceException(ExceptionCodes code){
        super(code.getCode(), code.getMessage());
        httpStatus = code.getHttpStatus();
    }

    public ServiceException(ExceptionCodes code, Object detail){
        super(code.getCode(), code.getMessage(), detail);
        this.httpStatus = code.getHttpStatus();
    }
}
