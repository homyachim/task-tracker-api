package com.khomel.auth.service;

import com.khomel.repository.UserRepository;
import com.khomel.model.dto.LoginBodyDto;
import com.khomel.model.dto.RegistrationBodyDto;
import com.khomel.model.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signUp(RegistrationBodyDto body) {
        User user = User.builder()
                .username(body.getUsername())
                .email(body.getEmail())
                .password(passwordEncoder.encode(body.getPassword()))
                .registeredAt(LocalDate.now())
                .build();

        return userRepository.save(user);
    }

    public User signIn(LoginBodyDto body) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        body.getUsername(),
                        body.getPassword()
                )
        );

        return userRepository.findByUsername(body.getUsername()).orElseThrow();
    }
}
