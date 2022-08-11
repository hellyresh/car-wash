package com.example.carwash.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCreateDto {
    @Length(min = 3, max = 20)
    private String firstName;
    private String lastName;
    @Length(min = 3, max = 20)
    private String username;
    @NotBlank
    @Length(min = 8, max = 30)
    //@Pattern(regexp = "(?=.*\d)(?=.*[a-z])(?=.*[A-Z])")
    private String password;
}
