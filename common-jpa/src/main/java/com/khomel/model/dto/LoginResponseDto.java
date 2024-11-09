package com.khomel.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponseDto {
    private String token;
    private Long expiresIn;
}
