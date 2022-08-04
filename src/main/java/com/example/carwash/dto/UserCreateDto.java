package com.example.carwash.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreateDto {
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    private String username;
    private String password;
}
