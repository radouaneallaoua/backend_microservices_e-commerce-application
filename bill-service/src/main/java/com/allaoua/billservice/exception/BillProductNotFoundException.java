package com.allaoua.billservice.exception;

public class BillProductNotFoundException extends  RuntimeException {
    public BillProductNotFoundException() {}
    public BillProductNotFoundException(String message) {
        super(message);
    }
}
