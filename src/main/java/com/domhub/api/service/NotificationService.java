package com.domhub.api.service;

import com.domhub.api.model.Account;
import com.domhub.api.model.Notification;
import com.domhub.api.model.Notification.NotificationType;
import com.domhub.api.dto.request.NotificationRequest;
import com.domhub.api.dto.response.NotificationDTO;
import com.domhub.api.repository.AccountRepository;
import com.domhub.api.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public String createNotification(NotificationRequest request) {
        try {
            Notification notification = new Notification();
            notification.setTitle(request.getTitle());
            notification.setContent(request.getContent());

            // Convert type safely
            try {
                notification.setType(Notification.NotificationType.valueOf(request.getType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                return "Invalid notification type: " + request.getType();
            }

            notification.setCreatedBy(request.getCreatedBy());

            notificationRepository.save(notification);
            return "Notification created successfully";
        } catch (Exception e) {
            return "Notification creation failed: " + e.getMessage();
        }
    }
    
    public NotificationDTO getNotificationById(Integer id) {
        Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Notification not found")); 
        Account account = accountRepository.findById(notification.getCreatedBy())
        .orElseThrow(() -> new RuntimeException("Account not found"));
        return new NotificationDTO(
            notification.getTitle(),
            notification.getContent(),
            notification.getCreatedDate(),
            account.getUserName()
        );
    }
    
    public List<Notification> getNotificationsByType(NotificationType type) {
        return notificationRepository.findByType(type);
    }
}

