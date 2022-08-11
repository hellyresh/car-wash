package com.example.carwash.service;

import com.example.carwash.dto.operator.OperatorDto;
import com.example.carwash.dto.user.UserCreateDto;
import com.example.carwash.dto.user.UserDto;
import com.example.carwash.exception.EntityNotFoundException;
import com.example.carwash.exception.RoleCannotBeChangedException;
import com.example.carwash.exception.UsernameAlreadyExistsException;
import com.example.carwash.model.User;
import com.example.carwash.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.carwash.model.Role.OPERATOR;
import static com.example.carwash.model.Role.USER;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final OperatorService operatorService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Transactional
    public OperatorDto grantOperatorToUser(Long id) {
        User user = getUser(id);
        if (user.getRole().equals(USER)) {
            user.setRole(OPERATOR);
            userRepo.save(user);
            operatorService.create(user);
            return UserDto.toDto(user);
        }
        throw new RoleCannotBeChangedException(id, user.getRole(), OPERATOR);
    }

    public List<UserDto> getUsers() {
        return userRepo.findAll().stream().map(UserDto::toDto).toList();
    }


    @Transactional
    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = modelMapper.map(userCreateDto, User.class);
        user.setRole(USER);
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException(user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return modelMapper.map(user, UserDto.class);

    }

    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {

        return null;
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User", "username", username));
    }

    public User getUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", "id", id.toString()));
    }


}
