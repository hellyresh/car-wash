package com.example.carwash.controller;

import com.example.carwash.dto.user.UserCreateDto;
import com.example.carwash.dto.user.UserDto;
import com.example.carwash.dto.user.UserUpdateDto;
import com.example.carwash.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

//    @PostMapping
//    public UserDto createUser(@RequestBody UserCreateDto userCreateDto) {
//        return userService.createUser(userCreateDto);
//    }

    //TODO anon grant
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable Long id, UserUpdateDto userUpdateDto) {
        return userService.updateUser(id, userUpdateDto);
    }

    //TODO admin
    @PutMapping("{id}/grant")
    @ResponseStatus(HttpStatus.OK)
    public UserDto grantOperatorToUser(@PathVariable Long id) {
        return userService.grantOperatorToUser(id);
    }


}
