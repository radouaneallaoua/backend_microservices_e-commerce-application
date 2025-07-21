package com.allaoua.authservice.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponseDto {
    private Long id;
    private String roleName;
}
