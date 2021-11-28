package com.clint.marvel.exception;

public class ValidationException extends BaseServiceException{

    private static final long serialVersionUID = 1590032697265049879L;


    public ValidationException(String code, String msg, Object detail) {
        super(code, msg, detail);
    }

    public ValidationException(BaseServiceException e) {
        super(e);
    }

    public ValidationException(String code, String msg) {
        super(code, msg);
    }

    public ValidationException(String code) {
        super(code);
    }
}
