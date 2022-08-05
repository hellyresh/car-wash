package com.example.carwash.service;

import com.example.carwash.dto.user.UserCreateDto;
import com.example.carwash.dto.user.UserDto;
import com.example.carwash.dto.user.UserUpdateDto;
import com.example.carwash.model.Role;
import com.example.carwash.model.User;
import com.example.carwash.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;


    @Transactional
    public UserDto giveOperator(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException(format("Пользователя с id = %d нe существует", id)));
        user.setRole(Role.OPERATOR);
        //TODO new operator?
        userRepo.save(user);
        return UserDto.toDto(user);
    }

    public List<UserDto> getUsers() {
        return userRepo.findAll().stream().map(UserDto::toDto).toList();
    }

    @Transactional
    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = modelMapper.map(userCreateDto, User.class);
        userRepo.save(user);
        return modelMapper.map(user, UserDto.class);

    }

    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {

        return null;
    }
}
