package com.allaoua.authservice.dto;


import com.allaoua.authservice.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequestDto {
    private String roleName;
}
