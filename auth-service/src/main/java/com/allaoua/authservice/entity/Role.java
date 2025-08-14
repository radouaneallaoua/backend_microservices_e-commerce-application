package com.allaoua.authservice.entity;


import com.allaoua.authservice.dto.RoleResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;

    public RoleResponseDto toDto() {
        return RoleResponseDto.builder()
                .id(this.id)
                .roleName(this.roleName)
                .build();
    }



}
