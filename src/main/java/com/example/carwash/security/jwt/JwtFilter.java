package com.example.carwash.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtValidator jwtValidator;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.bearer}")
    private String bearer;
    @Value("${jwt.auth-header}")
    private String headerAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = parseToken(request);
            if (token != null && jwtValidator.validateToken(token)) {
                Claims claims = jwtValidator.getClaims(token);
                String username = claims.getSubject();
                Set<SimpleGrantedAuthority> authorities = getAuthorities(claims);
                Authentication authentication = new UsernamePasswordAuthenticationToken(username,
                        null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JwtException e) {
            throw new MalformedJwtException(String.format("Can not authorize token: %s",
                    request.getHeader(headerAuth)));
        }
        filterChain.doFilter(request, response);
    }

    private String parseToken(HttpServletRequest request) {
        String header = request.getHeader(headerAuth);
        if (StringUtils.hasText(request.getHeader(headerAuth)) && header.startsWith(bearer)) {
            return header.substring(7);
        }
        return null;
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        var role = (List<Map<String, String>>) claims.get("role");

        return role.stream()
                .map(map -> new SimpleGrantedAuthority(map.get("authority")))
                .collect(Collectors.toSet());

    }
}
