package com.allaoua.authservice.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter  @AllArgsConstructor @NoArgsConstructor @Builder
public class UserRequestDto {
    private String username;

    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private MultipartFile imageURL;
}
