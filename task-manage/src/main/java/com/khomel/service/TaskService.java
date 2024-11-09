package com.khomel.service;

import com.khomel.model.dto.TaskDto;
import com.khomel.model.entity.Task;
import com.khomel.model.entity.User;
import com.khomel.model.entity.enums.TaskState;
import com.khomel.model.message.TaskCreatedMessage;
import com.khomel.repository.TaskRepository;
import com.khomel.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository,
                       RabbitTemplate rabbitTemplate) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Task createTask(TaskDto taskDto) {
        User user = userRepository.findById(taskDto.getUserId())
                .orElseThrow();

        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(TaskState.TO_DO)
                .deadline(taskDto.getDeadline())
                .createdAt(LocalDate.now())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);

        TaskCreatedMessage message = new TaskCreatedMessage(savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getDeadline().toString(),
                savedTask.getUser().getId());

        rabbitTemplate.convertAndSend("task.exchange", "task.created", message);
        return task;
    }

    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        return optionalTask.orElseThrow();
    }

    public Task updateTask(Long id, TaskDto updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setDeadline(updatedTask.getDeadline());
        task.setFinishedAt(updatedTask.getFinishedAt());
        task.setStartedAt(updatedTask.getStartedAt());
        task.setCreatedAt(updatedTask.getCreatedAt());
        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        if (task.getStatus().equals(TaskState.TO_DO)) {
            task.setStatus(TaskState.IN_PROGRESS);
            task.setStartedAt(LocalDate.now());
        } else if (task.getStatus().equals(TaskState.IN_PROGRESS)) {
            task.setStatus(TaskState.DONE);
            task.setFinishedAt(LocalDate.now());
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
