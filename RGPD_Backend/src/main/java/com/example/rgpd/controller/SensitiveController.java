package com.example.rgpd.controller;

import com.example.rgpd.common.dto.auth.AuthRequest;
import com.example.rgpd.common.dto.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensitive")
@RequiredArgsConstructor
public class SensitiveController {

    //@PreAuthorize("hasAnyAuthority(RightsUser.ACCESS_SENSITIVE_DATA)")
}
