package com.allaoua.inventoryservice.exception;

public class ProductImageNotFoundException extends  RuntimeException {
    public ProductImageNotFoundException() {}
    public ProductImageNotFoundException(String message) {
        super(message);
    }
}
