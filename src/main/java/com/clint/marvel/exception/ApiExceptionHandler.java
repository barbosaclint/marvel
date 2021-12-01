package com.clint.marvel.exception;

import com.clint.marvel.util.MarvelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = { BaseServiceException.class})
    public ResponseEntity<Object> handleApiRequestException(BaseServiceException ex) {
        ApiError apiError = new ApiError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                MarvelUtil.instant + ""
        );

        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}
