package com.example.carwash.dto;

import com.example.carwash.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Role role;
}
