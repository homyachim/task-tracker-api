package com.khomel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@OpenAPIDefinition(
        info = @Info(
                title = "Notification Manage API",
                description = "Сервис уведомлений отвечает за создание и удаление нотификаций для задач. " +
                        "При появлении новой задачи или изменении её статуса сервис генерирует уведомления, " +
                        "информируя пользователей о важных событиях, связанных с задачами. Удаление нотификации " +
                        "осуществляется по завершении задачи или при её удалении из системы. Сервис тесно " +
                        "интегрирован с другими компонентами системы управления задачами, обеспечивая своевременное " +
                        "информирование пользователей",
                version = "1.0.0"
        )
)
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages = "com.khomel.model.entity")
@EnableJpaRepositories(basePackages = "com.khomel.repository")
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}