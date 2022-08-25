package com.example.carwash.security.service;

import com.example.carwash.model.User;
import com.example.carwash.repository.UserRepo;
import com.example.carwash.security.CustomUserDetails;
import com.example.carwash.security.dto.AppUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this username: " + username));
        return new CustomUserDetails(AppUserDto.toDto(user));
    }

}
