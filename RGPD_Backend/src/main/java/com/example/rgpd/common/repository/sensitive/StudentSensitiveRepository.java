package com.example.rgpd.common.repository.sensitive;

import com.example.rgpd.common.entity.sensitive.StudentSensitiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSensitiveRepository extends JpaRepository<StudentSensitiveEntity, Long> {
}
