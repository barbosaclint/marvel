package com.clint.marvel.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ApiError {
    private HttpStatus httpStatus;
    private String timestamp;
    private String message;
    private String debugMessage;

    public ApiError(String message, HttpStatus httpStatus, String timestamp){
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
