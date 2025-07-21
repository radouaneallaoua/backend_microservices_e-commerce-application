package com.allaoua.billservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {


    @ExceptionHandler(value = BillNotFoundException.class)
    public ResponseEntity<ErrorMessage> billNotFound(BillNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value =BillProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> billProductNotFound(BillProductNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
