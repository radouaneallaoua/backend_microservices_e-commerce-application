package com.allaoua.billservice.models;


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
