package com.jyotirmay.tinyurl.exception;

public class InvalidTinyURLException extends RuntimeException{

    private final String errorCode;

    public InvalidTinyURLException(String errorCode, String errorMessage, Object... args){
        super(String.format(errorMessage,args));
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}