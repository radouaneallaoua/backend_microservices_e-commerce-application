package com.allaoua.inventoryservice.exception;

public class ColorNotFoundException extends  RuntimeException {
    public ColorNotFoundException() {}
    public ColorNotFoundException(String message) {
        super(message);
    }
}
