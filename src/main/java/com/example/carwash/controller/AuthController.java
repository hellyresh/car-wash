package com.example.carwash.controller;

import com.example.carwash.dto.user.UserCreateDto;
import com.example.carwash.dto.user.UserDto;
import com.example.carwash.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }
}
