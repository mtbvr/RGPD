package com.example.rgpd.common.repository.identity;

import com.example.rgpd.common.entity.identity.StudentSchoolLifeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentSchoolLifeRepository extends JpaRepository<StudentSchoolLifeEntity, Long> {
    Optional<StudentSchoolLifeEntity> findByStudentIdentityId(Long studentId);
}
