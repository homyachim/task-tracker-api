package com.khomel.controller;

import com.khomel.model.dto.TaskDto;
import com.khomel.model.entity.Task;
import com.khomel.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task-api")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskDto) {
        log.info("Creating task: {}", taskDto);
        Task createdTask = taskService.createTask(taskDto);
        log.info("Task created successfully: {}", createdTask.getId());
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        log.info("Getting task with id: {}", id);
        Task task = taskService.getTaskById(id);
        log.info("Task retrieved: {}", task.getTitle());
        return ResponseEntity.ok(task);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        log.info("Updating task with id: {}. New details: {}", id, taskDto);
        Task updatedTask = taskService.updateTask(id, taskDto);
        log.info("Task updated successfully: {}", updatedTask.getTitle());
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/update/status/{id}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id) {
        log.info("Updating status for task with id: {}", id);
        Task updatedTask = taskService.updateTaskStatus(id);
        log.info("Task status updated successfully: {}", updatedTask.getStatus());
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        log.info("Deleting task with id: {}", id);
        taskService.deleteTask(id);
        log.info("Task with id {} deleted successfully", id);
    }
}