package com.allaoua.billservice.exception;

import lombok.*;

import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Builder @Setter
@Getter
public class ErrorMessage {
    private String message;
    private int code;
    private Date timestamp;
}
