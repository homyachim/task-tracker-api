package com.khomel.auth.controller;

import com.khomel.auth.service.UserService;
import com.khomel.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-api")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        log.info("Authenticated user with id={}", currentUser.getId());

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> allUsers() {
        log.info("Request to get all users");

        List<User> users = userService.allUsers();

        log.info("Retrieved {} users", users.size());
        return ResponseEntity.ok(users);
    }
}