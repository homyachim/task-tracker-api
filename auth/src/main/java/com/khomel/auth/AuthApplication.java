package com.khomel.auth;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(
		info = @Info(
				title = "Auth Service API",
				description = "Сервис авторизации отвечает за управление доступом пользователей в системе, " +
						"предоставляя возможности для регистрации, входа, аутентификации и выхода из системы. " +
						"Он проверяет подлинность учетных данных и назначает роли и права доступа, обеспечивая " +
						"защиту данных и корректное разграничение прав. Сервис также генерирует и обрабатывает " +
						"токены для поддержания сеансов пользователей и взаимодействует с другими компонентами " +
						"для контроля доступа к задачам и уведомлениям.",
				version = "1.0.0"
		)
)
@SpringBootApplication
@EntityScan(basePackages = "com.khomel.model.entity")
@EnableJpaRepositories(basePackages = "com.khomel.repository")
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
