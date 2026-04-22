package com.example.rgpd.controller;

import com.example.rgpd.common.dto.auth.AuthRequest;
import com.example.rgpd.common.dto.auth.AuthResponse;
import com.example.rgpd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody AuthRequest request) {
        return userService.signup(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return userService.login(request);
    }
}