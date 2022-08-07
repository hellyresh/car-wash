package com.example.carwash.service;

import com.example.carwash.dto.user.UserCreateDto;
import com.example.carwash.dto.user.UserDto;
import com.example.carwash.dto.user.UserUpdateDto;
import com.example.carwash.exception.EntityNotFoundException;
import com.example.carwash.model.Role;
import com.example.carwash.model.User;
import com.example.carwash.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;


    @Transactional
    public UserDto grantOperatorToUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        //TODO check role
        user.setRole(Role.OPERATOR);

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
