package com.example.carwash.controller;

import com.example.carwash.dto.user.UserCreateDto;
import com.example.carwash.dto.user.UserDto;
import com.example.carwash.dto.user.UserUpdateDto;
import com.example.carwash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable Long id, UserUpdateDto userUpdateDto) {
        return userService.updateUser(id, userUpdateDto);
    }

    //TODO admin
    @PutMapping("{id}/grant")
    public UserDto giveOperator(@PathVariable Long id) {
        return userService.giveOperator(id);
    }




}
