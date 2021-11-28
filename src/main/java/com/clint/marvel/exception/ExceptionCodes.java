package com.clint.marvel.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCodes {

    SERVER_ERROR("0002","Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVER_UNAVAILABLE("0003","Server Error", HttpStatus.OK),
    SOME_OTHER_EXCEPTIONS("0004","Dont KnowError", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
