package com.khomel.repository;

import com.khomel.model.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findById(Long id);
    Task save(Task task);
    void deleteById(Long id);
}
