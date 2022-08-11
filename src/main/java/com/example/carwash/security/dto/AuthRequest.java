package com.example.carwash.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthRequest {
    @NotBlank(message = "Name should not be empty")
    private String username;
    @NotBlank
    private String password;
}
