package com.khomel.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationBodyDto {

    @NotNull
    @Size(min = 3, max = 30)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    //Minimum eight characters, at least one letter and one number:
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,32}$")
    private String password;
}
