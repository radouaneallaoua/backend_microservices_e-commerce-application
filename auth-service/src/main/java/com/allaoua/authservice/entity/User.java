package com.allaoua.authservice.entity;


import com.allaoua.authservice.dto.UserResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter  @AllArgsConstructor @NoArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String username;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    private String address;
    private String password;  //HACHAGE
    private String imageURL;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications =new ArrayList<>();

    public UserResponseDto toDto(){
        return UserResponseDto.builder()
                .id(id)
                .address(address)
                .imageURL(imageURL)
                .password(password)
                .phoneNumber(phoneNumber)
                .username(username)
                .email(email)
                .notifications(notifications.stream().map(Notification::toDto).toList())
                .roles(roles.stream().map(Role::toDto).toList())
                .build();
    }


}
