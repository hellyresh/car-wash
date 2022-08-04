package com.example.carwash.controller;

import com.example.carwash.dto.UserDto;
import com.example.carwash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //TODO admin
    @PutMapping("{id}/grant")
    public UserDto giveOperator(@PathVariable Long id) {
        return userService.giveOperator(id);
    }



}
