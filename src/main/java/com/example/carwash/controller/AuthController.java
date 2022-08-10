package com.example.carwash.controller;

import com.example.carwash.dto.user.UserCreateDto;
import com.example.carwash.dto.user.UserDto;
import com.example.carwash.security.dto.AuthRequest;
import com.example.carwash.security.service.AuthService;
import com.example.carwash.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthService authService;

    @PostMapping("api/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }


    @PostMapping("api/login")
    public ResponseEntity<?> authorize(@Valid @RequestBody AuthRequest request) {
        try {
            userDetailsService.loadUserByUsername(request.getUsername());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(authService.authUser(request));
    }

}
