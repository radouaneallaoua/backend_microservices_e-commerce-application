package com.allaoua.inventoryservice.exception;

public class SizeNotFoundException extends  RuntimeException {
    public SizeNotFoundException() {}
    public SizeNotFoundException(String message) {
        super(message);
    }
}
