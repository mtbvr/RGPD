package com.example.rgpd.service;

import com.example.rgpd.common.dto.sensitive.LightSensitive;

import java.util.List;

public interface SensitiveService {
    List<LightSensitive> findAll();
}
