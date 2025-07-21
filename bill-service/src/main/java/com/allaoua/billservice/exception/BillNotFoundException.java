package com.allaoua.billservice.exception;

public class BillNotFoundException extends  RuntimeException {
    public BillNotFoundException() {}
    public BillNotFoundException(String message) {
        super(message);
    }
}
