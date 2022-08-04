package com.example.carwash.dto;

import com.example.carwash.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    @NotNull
    private Long id;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    private String username;
    public static UserDto toDto(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
