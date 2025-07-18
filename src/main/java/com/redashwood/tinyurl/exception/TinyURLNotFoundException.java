package com.redashwood.tinyurl.exception;

public class TinyURLNotFoundException extends RuntimeException{

    private final String errorCode;

    public TinyURLNotFoundException(String errorCode, String errorMessage, Object... args){
        super(String.format(errorMessage,args));
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
