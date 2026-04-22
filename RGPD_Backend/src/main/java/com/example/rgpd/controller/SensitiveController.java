package com.example.rgpd.controller;

import com.example.rgpd.common.dto.auth.AuthRequest;
import com.example.rgpd.common.dto.auth.AuthResponse;
import com.example.rgpd.common.dto.sensitive.LightSensitive;
import com.example.rgpd.service.SensitiveService;
import com.example.rgpd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensitive")
@RequiredArgsConstructor
public class SensitiveController {

    private final SensitiveService sensitiveService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority(RightsUser.ACCESS_SENSITIVE_DATA)")
    public List<LightSensitive> findAllSensitive() {
        return sensitiveService.findAll();
    }

}
