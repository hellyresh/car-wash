package com.example.carwash.security.jwt;

import com.example.carwash.security.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtGenerator {
    @Value("${jwt.secret-key}")
    private String jwtSecret;
    @Value("${jwt.session-time.access}")
    private int accessTime;
    @Value("${jwt.session-time.refresh}")
    private int refreshTime;

    public String generateToken(CustomUserDetails user, int expirationTime) {
        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(issuedAt.plus(expirationTime, ChronoUnit.MILLIS)))
                .claim("role", user.getAuthorities())
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public String generateAccessToken(CustomUserDetails user) {
        return generateToken(user, accessTime);
    }

    public String generateRefreshToken(CustomUserDetails user) {
        return generateToken(user, refreshTime);
    }


}
