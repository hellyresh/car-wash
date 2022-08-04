package com.example.carwash.service;

import com.example.carwash.dto.UserDto;
import com.example.carwash.model.Role;
import com.example.carwash.model.User;
import com.example.carwash.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static java.lang.String.format;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDto giveOperator(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException(format("Пользователя с id = %d нe существует", id)));
        user.setRole(Role.OPERATOR);
        userRepo.save(user);
        return UserDto.toDto(user);
    }
}
