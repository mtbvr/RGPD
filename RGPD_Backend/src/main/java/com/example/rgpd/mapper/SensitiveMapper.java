package com.example.rgpd.mapper;

import com.example.rgpd.common.dto.sensitive.LightSensitive;
import com.example.rgpd.common.entity.sensitive.StudentSensitiveEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SensitiveMapper {

    public LightSensitive toLightDto(StudentSensitiveEntity entity) {

        String allergy = entity.getIsAnonymized() != null && entity.getIsAnonymized()
                ? "ANONYMIZED"
                : entity.getAllergy();

        return new LightSensitive(
                entity.getId(),
                allergy
        );
    }

    public List<LightSensitive> toLightDto(List<StudentSensitiveEntity> entities) {
        return entities.stream()
                .map(this::toLightDto)
                .toList();
    }
}