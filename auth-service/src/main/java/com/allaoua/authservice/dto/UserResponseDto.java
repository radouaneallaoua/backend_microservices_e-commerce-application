package com.allaoua.authservice.dto;


import lombok.*;

import java.util.List;

@Getter @Setter  @AllArgsConstructor @NoArgsConstructor @Builder
public class UserResponseDto {
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private String imageURL;
    private List<RoleResponseDto> roles;
    private List<NotificationResponseDto> notifications;

}
