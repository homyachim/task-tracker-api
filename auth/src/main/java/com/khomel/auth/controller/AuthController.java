package com.khomel.auth.controller;

import com.khomel.auth.service.AuthService;
import com.khomel.auth.service.JwtService;
import com.khomel.model.dto.LoginBodyDto;
import com.khomel.model.dto.LoginResponseDto;
import com.khomel.model.dto.RegistrationBodyDto;
import com.khomel.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-api")
@Slf4j
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> signUp(@RequestBody RegistrationBodyDto body) {
        log.info("Received registration request for user with username={}", body.getUsername());

        User user = authService.signUp(body);

        log.info("User with id={} successfully registered", user.getId());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> signIn(@RequestBody LoginBodyDto body) {
        log.info("Received login request for user with username={}", body.getUsername());

        User user = authService.signIn(body);

        String jwtToken = jwtService.generateToken(user);

        LoginResponseDto loginResponse = LoginResponseDto.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getJwtExpiration())
                .build();

        log.info("User with id={} successfully logged in, JWT token generated", user.getId());
        return ResponseEntity.ok(loginResponse);
    }
}
