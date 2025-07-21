package com.allaoua.billservice.models;


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
    private RoleResponseDto role;
    private List<NotificationResponseDto> notifications;

}
