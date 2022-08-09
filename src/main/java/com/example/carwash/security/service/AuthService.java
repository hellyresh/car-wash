package com.example.carwash.security.service;

import com.example.carwash.security.CustomUserDetails;
import com.example.carwash.security.dto.AuthRequest;
import com.example.carwash.security.dto.AuthResponse;
import com.example.carwash.security.dto.RefreshRequest;
import com.example.carwash.security.jwt.JwtGenerator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomUserDetailsService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final JwtValidator jwtValidator;
    private final Map<String, String> tokensMap = new HashMap<>();



    public AuthResponse authUser(AuthRequest AuthRequest) throws AuthException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(AuthRequest.getUsername(), AuthRequest.getPassword())
            );
        } catch (BadCredentialsException exception) {
            throw new AuthException();
        }
        UserDetails user = userService.loadUserByUsername(AuthRequest.getUsername());
        String accessToken = jwtGenerator.generateAccessToken((CustomUserDetails) user);
        String refreshToken = jwtGenerator.generateRefreshToken((CustomUserDetails) user);
        tokensMap.put(user.getUsername(), refreshToken);
        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(RefreshRequest refreshRequest) throws AuthException, AuthenticationException {
        String refreshToken = refreshRequest.getRefreshToken();
        if (jwtValidator.validateToken(refreshToken)) {
            Claims claims = jwtValidator.getClaims(refreshToken);
            String username = claims.getSubject();
            String refreshTokenFromMap = tokensMap.get(username);
            if (refreshTokenFromMap != null && refreshTokenFromMap.equals(refreshToken)) {
                CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(username);
                String newAccessToken = jwtGenerator.generateAccessToken(user);
                return new AuthResponse(newAccessToken, refreshToken);
            }
        }
        throw new AuthException("Invalid refresh token");
    }



}
