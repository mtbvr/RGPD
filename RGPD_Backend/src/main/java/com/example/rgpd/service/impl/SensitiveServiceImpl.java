package com.example.rgpd.service.impl;

import com.example.rgpd.common.dto.sensitive.LightSensitive;
import com.example.rgpd.common.entity.sensitive.StudentSensitiveEntity;
import com.example.rgpd.common.repository.sensitive.StudentSensitiveRepository;
import com.example.rgpd.mapper.SensitiveMapper;
import com.example.rgpd.service.SensitiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;


@Slf4j
@Service
@RequiredArgsConstructor
public class SensitiveServiceImpl implements SensitiveService {

    private final StudentSensitiveRepository repository;
    private final SensitiveMapper mapper;

    @Override
    public List<LightSensitive> findAll() {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("SENSITIVE_READ_ALL user={}", user);

        List<StudentSensitiveEntity> entities = repository.findAll();

        return mapper.toLightDto(entities);
    }

    @Override
    public LightSensitive findById(Long id) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("SENSITIVE_READ user={} entityId={}", user, id);

        StudentSensitiveEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensitive data not found"));

        return mapper.toLightDto(entity);
    }

    @Override
    public LightSensitive create(LightSensitive dto) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        log.warn("SENSITIVE_CREATE user={}", user);

        StudentSensitiveEntity entity = new StudentSensitiveEntity();
        entity.setAllergy(dto.allergy());
        entity.setIsAnonymized(false);

        repository.save(entity);

        return mapper.toLightDto(entity);
    }

    @Override
    public void delete(Long id) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        log.warn("SENSITIVE_DELETE user={} entityId={}", user, id);

        StudentSensitiveEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensitive data not found"));

        // 🔐 RGPD : pas de delete physique
        entity.setDeletedAt(Instant.now());
        entity.setIsAnonymized(true);
        entity.setAllergy(null);

        repository.save(entity);
    }
}
