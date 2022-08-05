package com.example.carwash.dto.user;

import com.example.carwash.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserUpdateDto {
    @NotNull
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Role role;
}
