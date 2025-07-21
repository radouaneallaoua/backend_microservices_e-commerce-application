package com.allaoua.inventoryservice.exception;

public class ProductColorNotFoundException extends  RuntimeException {
    public ProductColorNotFoundException() {}
    public ProductColorNotFoundException(String message) {
        super(message);
    }
}
