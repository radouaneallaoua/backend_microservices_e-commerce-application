package com.allaoua.authservice.service;

import com.allaoua.authservice.dto.NotificationRequestDto;
import com.allaoua.authservice.dto.NotificationResponseDto;
import com.allaoua.authservice.entity.Notification;
import com.allaoua.authservice.enums.NotificationStatus;
import com.allaoua.authservice.repository.NotificationRepository;
import com.allaoua.authservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NotificationService {
    final private NotificationRepository notificationRepository;
    final private UserRepository userRepository;
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<NotificationResponseDto> saveNotification(NotificationRequestDto notificationRequestDto) {
         Notification notification= Notification.builder()
                 .notificationStatus(NotificationStatus.NOT_READIED)
                 .content(notificationRequestDto.getContent())
                 .title(notificationRequestDto.getTitle())
                 .date(new Date())
                 .user(userRepository.findById(notificationRequestDto.getUserId()).orElse(null))
                 .build();
         return ResponseEntity.ok(notificationRepository.save(notification).toDto());
    }

    public ResponseEntity<List<NotificationResponseDto>> getAllNotifications() {
        return ResponseEntity.ok(notificationRepository.findAll().stream().map(Notification::toDto).toList());
    }

    public ResponseEntity<List<NotificationResponseDto>> getAllNotificationsWithUserId(String userId) {
        return ResponseEntity.ok(notificationRepository.findByUserId(userId).stream().map(Notification::toDto).toList());
    }



    public ResponseEntity<NotificationResponseDto> getNotificationById(Long id) {
        return ResponseEntity.ok(notificationRepository.findById(id).get().toDto());
    }

    public ResponseEntity<NotificationResponseDto> updateNotification(Long id) {
        Notification notification = notificationRepository.findById(id).get();
        notification.setNotificationStatus(NotificationStatus.READIED);
        return ResponseEntity.ok(notificationRepository.save(notification).toDto());
    }
}
