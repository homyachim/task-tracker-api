package com.khomel.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBodyDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
