package com.clint.marvel.exception;

import com.clint.marvel.util.MarvelUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseServiceException extends RuntimeException{

    private final String status;
    private final String timeStamp;
    private final HttpStatus httpStatus;

    public BaseServiceException(Throwable throwable, HttpStatus httpStatus, String timeStamp) {
        this.status = throwable.getMessage();
        this.timeStamp = timeStamp;
        this.httpStatus = httpStatus;
    }

    public BaseServiceException(Throwable throwable) {
        this.status = throwable.getMessage();
        this.timeStamp = null;
        this.httpStatus = null;
    }
}
