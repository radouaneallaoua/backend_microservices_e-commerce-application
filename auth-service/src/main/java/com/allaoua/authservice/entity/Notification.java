package com.allaoua.authservice.entity;


import com.allaoua.authservice.dto.NotificationResponseDto;
import com.allaoua.authservice.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String title;
    private String content;
    private NotificationStatus notificationStatus;

    @ManyToOne
    private User user;

    public NotificationResponseDto toDto(){
        return  NotificationResponseDto.builder()
                .content(content)
                .id(id)
                .title(title)
                .userId(user.getId())
                .notificationStatus(notificationStatus)
                .build();
    }


}
