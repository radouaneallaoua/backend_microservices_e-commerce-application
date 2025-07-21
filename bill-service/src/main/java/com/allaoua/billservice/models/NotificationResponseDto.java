package com.allaoua.billservice.models;


import com.allaoua.billservice.models.NotificationStatus;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponseDto {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private Date date;
    private NotificationStatus notificationStatus;


}
