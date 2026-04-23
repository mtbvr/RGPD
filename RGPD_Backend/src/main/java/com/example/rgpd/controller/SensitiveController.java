package com.example.rgpd.controller;

import com.example.rgpd.common.dto.sensitive.LightSensitive;
import com.example.rgpd.service.SensitiveService;
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
    @PreAuthorize("hasAnyAuthority('ACCESS_SENSITIVE_DATA')")
    public List<LightSensitive> findAllSensitive() {
        return sensitiveService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACCESS_SENSITIVE_DATA')")
    public LightSensitive findById(@PathVariable Long id) {
        return sensitiveService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('RECTIFY_SENSITIVE_DATA')")
    public LightSensitive create(@RequestBody LightSensitive dto) {
        return sensitiveService.create(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_SENSITIVE_DATA')")
    public void delete(@PathVariable Long id) {
        sensitiveService.delete(id);
    }
}