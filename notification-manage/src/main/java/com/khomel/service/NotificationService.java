package com.khomel.service;

import com.khomel.model.entity.Notification;
import com.khomel.model.message.TaskCreatedMessage;
import com.khomel.repository.NotificationRepository;
import com.khomel.repository.TaskRepository;
import com.khomel.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public NotificationService(NotificationRepository notificationRepository,
                               UserRepository userRepository,
                               TaskRepository taskRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @RabbitListener(queues = "task.created.queue")
    private void handleTaskCreated(TaskCreatedMessage message) {
        Notification notification = Notification.builder()
                .task(taskRepository.findById(message.getTaskId()).orElseThrow())
                .title(message.getTitle())
                .text(message.getDescription())
                .sendAt(LocalDate.parse(message.getDeadLine()).minusDays(1))
                .user(userRepository.findById(message.getUserId()).orElseThrow())
                .build();

        createNotification(notification);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
