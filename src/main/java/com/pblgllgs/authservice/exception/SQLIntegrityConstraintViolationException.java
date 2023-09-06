package com.pblgllgs.authservice.exception;

public class SQLIntegrityConstraintViolationException extends RuntimeException{
    private String msg;
    public SQLIntegrityConstraintViolationException(String message) {
        super(message);
        this.msg = message;
    }
}
