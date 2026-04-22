package com.example.rgpd.service;

import com.example.rgpd.common.dto.auth.AuthRequest;
import com.example.rgpd.common.dto.auth.AuthResponse;

public interface UserService {
    AuthResponse signup(AuthRequest request);
    AuthResponse login(AuthRequest request);
}