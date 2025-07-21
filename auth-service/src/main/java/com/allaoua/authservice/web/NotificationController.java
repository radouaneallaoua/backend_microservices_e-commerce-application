package com.allaoua.authservice.web;

import com.allaoua.authservice.dto.NotificationRequestDto;
import com.allaoua.authservice.dto.NotificationResponseDto;
import com.allaoua.authservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("")
    public ResponseEntity<NotificationResponseDto> saveNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        return notificationService.saveNotification(notificationRequestDto);
    }

    @GetMapping("")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotificationsWithUserId(@PathVariable String userId) {
        return notificationService.getAllNotificationsWithUserId(userId);
    }
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponseDto> getNotificationById(@PathVariable Long notificationId) {
        return notificationService.getNotificationById(notificationId);
    }

    @PutMapping("/{notificationId}")
    public ResponseEntity<NotificationResponseDto> updateNotification(@PathVariable Long notificationId) {
        return notificationService.updateNotification(notificationId);
    }


}
