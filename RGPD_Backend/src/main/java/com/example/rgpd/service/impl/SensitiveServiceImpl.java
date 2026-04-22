package com.example.rgpd.service.impl;

import com.example.rgpd.common.dto.sensitive.LightSensitive;
import com.example.rgpd.common.entity.sensitive.StudentSensitiveEntity;
import com.example.rgpd.common.repository.sensitive.StudentSensitiveRepository;
import com.example.rgpd.mapper.SensitiveMapper;
import com.example.rgpd.service.SensitiveService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensitiveServiceImpl implements SensitiveService {

    private StudentSensitiveRepository studentSensitiveRepository;
    private SensitiveMapper sensitiveMapper;

    @Override
    public List<LightSensitive> findAll() {
        List<StudentSensitiveEntity> sensitives = studentSensitiveRepository.findAll();
        return sensitiveMapper.toLightDto(sensitives);
    }
}
