package com.example.rgpd.service.impl;

import com.example.rgpd.common.dto.auth.AuthRequest;
import com.example.rgpd.common.dto.auth.AuthResponse;
import com.example.rgpd.common.entity.security.UserEntity;
import com.example.rgpd.common.repository.security.UserRepository;
import com.example.rgpd.common.utils.JwtUtils;
import com.example.rgpd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(AuthRequest request) {
        if (userRepository.findByLogin(request.login()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setLogin(request.login());
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getLogin());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        UserEntity user = userRepository.findByLogin(request.login())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getLogin());
        return new AuthResponse(token);
    }
}
