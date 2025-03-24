package com.domhub.api.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.domhub.api.model.Account;
import com.domhub.api.model.Notification;
import com.domhub.api.model.Notification.NotificationType;
import com.domhub.api.repository.AccountRepository;
import com.domhub.api.dto.request.NotificationRequest;
import com.domhub.api.dto.response.NotificationDTO;
import com.domhub.api.service.AccountService;
import com.domhub.api.service.NotificationService;

import io.jsonwebtoken.lang.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final AccountRepository accountRepository;

    private final NotificationService notificationService;


    @GetMapping("/findAll")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/create")      
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createNotification(@RequestBody NotificationRequest request) {
        String result = notificationService.createNotification(request);

        if (!result.contains("successfully")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
        
    }
    @GetMapping("/types")
    public ResponseEntity<List<String>> getNotificationTypes() {
        List<String> types = new ArrayList<>();
        for (Notification.NotificationType type : Notification.NotificationType.values()) {
            types.add(type.name());
        }
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public NotificationDTO getNotificationById(@PathVariable Integer id) {
        Notification notification=notificationService.getNotificationById(id);
        Account account = accountRepository.getById(notification.getCreatedBy());
        return new NotificationDTO(
            notification.getTitle(),
            notification.getContent(),
            notification.getCreatedDate(),
            account.getUserName()
        );
    }
    
    @GetMapping("/type/{type}")
    public List<Notification> getNotifications(@PathVariable String type) {
        NotificationType notificationType = NotificationType.valueOf(type);
        return notificationService.getNotificationsByType(notificationType);
    }
}
