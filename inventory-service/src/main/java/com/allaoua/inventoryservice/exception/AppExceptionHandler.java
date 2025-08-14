package com.allaoua.inventoryservice.exception;

import com.allaoua.inventoryservice.entity.Color;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = BrandNotFoundException.class)
    public ResponseEntity<ErrorMessage> brandNotFound(BrandNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<ErrorMessage> categoryNotFound(CategoryNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ColorNotFoundException.class)
    public ResponseEntity<ErrorMessage> colorNotFound(ColorNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductImageNotFoundException.class)
    public ResponseEntity<ErrorMessage> productImageNotFound(ProductImageNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFound(ProductNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductSizeNotFoundException.class)
    public ResponseEntity<ErrorMessage> productSizeNotFound(ProductSizeNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductColorNotFoundException.class)
    public ResponseEntity<ErrorMessage> productColorNotFound(ProductColorNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SizeNotFoundException.class)
    public ResponseEntity<ErrorMessage> sizeNotFound(SizeNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
