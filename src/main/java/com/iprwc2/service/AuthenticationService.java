package com.iprwc2.service;

import com.iprwc2.exception.UsernameAlreadyExistsException;
import com.iprwc2.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iprwc2.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public void register(RegisterRequest request, HttpServletResponse response) {
        if (repository.existsByEmail(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        var user = User.builder()
                .email(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .rights(request.getRights())
                .build();
        repository.save(user);
        setTokensInCookies(user, response);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        setTokensInCookies(user, response);

        return AuthenticationResponse.builder().id(user.getId()).build();
    }

    private void setTokensInCookies(User user, HttpServletResponse response) {
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Create and add JWT token cookie
        Cookie jwtTokenCookie = new Cookie("jwt-token", jwtToken);
        jwtTokenCookie.setHttpOnly(true); // Set to true for security reasons
        jwtTokenCookie.setMaxAge(86400); // 1 day
        jwtTokenCookie.setPath("/");
        response.addCookie(jwtTokenCookie);

        // Create and add refresh token cookie
        Cookie refreshTokenCookie = new Cookie("refresh-token", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge(604800);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        Cookie userIdCookie = new Cookie("user-id", String.valueOf(user.getId()));
        userIdCookie.setHttpOnly(false);
        userIdCookie.setMaxAge(86400);
        userIdCookie.setPath("/");
        response.addCookie(userIdCookie);
    }



    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refreshToken = extractCookieValue(request, "refresh-token");

        if (refreshToken == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No refresh token in cookies");
            return;
        }

        final String userName = jwtService.extractUsername(refreshToken);
        if (userName != null) {
            var user = this.repository.findByEmail(userName).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = generateAccessToken(user);
                addCookieToResponse(response, "jwt-token", accessToken);

                var authResponse = RefreshResponse.builder()
                        .token(accessToken)
                        .refreshToken(refreshToken)
                        .id(user.getId())
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private String extractCookieValue(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void addCookieToResponse(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(86400);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private String generateAccessToken(User user) {
        return jwtService.generateToken(user);
    }


}

