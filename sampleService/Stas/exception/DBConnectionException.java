package com.sysco.sampleService.Stas.exception;

public class DBConnectionException extends RuntimeException{
    public DBConnectionException(String message) {
        super(message);
    }
}
