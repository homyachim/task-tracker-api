package com.khomel.controller;

import com.khomel.model.entity.Notification;
import com.khomel.model.entity.Task;
import com.khomel.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify-api")
@Slf4j
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<Notification> createTask(@RequestBody Notification notification) {
        log.info("Creating notification: {}", notification.getId());
        Notification createdNotification = notificationService.createNotification(notification);
        log.info("Notification created successfully: {}", createdNotification.getTitle());
        return ResponseEntity.ok(createdNotification);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        log.info("Deleting notification with id: {}", id);
        notificationService.deleteNotification(id);
        log.info("Notification with id {} deleted successfully", id);
    }
}
