package com.khomel.repository;

import com.khomel.model.entity.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    Notification save(Notification notification);
    void deleteById(Long id);
}
