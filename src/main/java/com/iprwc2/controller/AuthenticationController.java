package com.iprwc2.controller;


import com.iprwc2.model.AuthenticationRequest;
import com.iprwc2.model.AuthenticationResponse;
import com.iprwc2.model.RegisterRequest;
import com.iprwc2.model.UserRegistrationRequest;
import com.iprwc2.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request,
            HttpServletResponse response
    ){
        service.register(request, response);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ){
        AuthenticationResponse authResponse = service.authenticate(request, response);
        return ResponseEntity.ok(authResponse);
    }


    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(
            @RequestBody UserRegistrationRequest request,
            HttpServletResponse response
    ){
        service.registerUser(request, response);
        return ResponseEntity.ok().build();
    }

}
