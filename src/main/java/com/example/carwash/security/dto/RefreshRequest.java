package com.example.carwash.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RefreshRequest {
    @NotEmpty(message = "Refresh token should not be empty")
    private String refreshToken;
}
