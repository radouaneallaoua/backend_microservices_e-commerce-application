package com.allaoua.authservice.dto;


import com.allaoua.authservice.entity.User;
import com.allaoua.authservice.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequestDto {
    private String title;
    private String content;
    private String userId;

}
