package com.allaoua.inventoryservice.exception;

public class ProductSizeNotFoundException extends  RuntimeException {
    public ProductSizeNotFoundException() {}
    public ProductSizeNotFoundException(String message) {
        super(message);
    }
}
