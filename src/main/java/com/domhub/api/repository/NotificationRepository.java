package com.domhub.api.repository;

import com.domhub.api.model.Notification;
import com.domhub.api.model.Notification.NotificationType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByType(NotificationType type);
}