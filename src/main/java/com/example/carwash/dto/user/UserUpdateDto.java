package com.example.carwash.dto.user;

import com.example.carwash.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String username;
    private Role role;
}
