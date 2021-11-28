package com.clint.marvel.exception;

import lombok.Getter;

@Getter
public class BaseServiceException extends RuntimeException {

    private static final long serialVersionUID = 9000407860332921360L;
    private final String code;
    private final String msg;
    private final transient Object detail;

    public BaseServiceException(String code, String msg, Object detail){
        super(msg);
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public BaseServiceException(BaseServiceException e){
        super(e.getMsg());
        this.code = e.getCode();
        this.msg = e.getMsg();
        this.detail = e.getDetail();
    }

    public BaseServiceException(String code, String msg){
        throw new BaseServiceException(code, msg, null);
    }

    public BaseServiceException(String code){
        throw new BaseServiceException(code, null, null);
    }

}
