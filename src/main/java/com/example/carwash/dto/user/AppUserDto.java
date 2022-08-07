package com.example.carwash.dto.user;

import com.example.carwash.model.Role;
import com.example.carwash.model.User;
import lombok.Getter;
import org.springframework.context.annotation.Bean;


@Getter
public class AppUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role;

    public static AppUserDto toDto(User user) {
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.id = user.getId();
        appUserDto.firstName = user.getFirstName();
        appUserDto.lastName = user.getLastName();
        appUserDto.username = user.getUsername();
        appUserDto.password = user.getPassword();
        appUserDto.role = user.getRole();
        return appUserDto;
    }
}
