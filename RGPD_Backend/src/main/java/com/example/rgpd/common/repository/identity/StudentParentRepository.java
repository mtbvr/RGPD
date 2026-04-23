package com.example.rgpd.common.repository.identity;

import com.example.rgpd.common.entity.identity.StudentParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentParentRepository extends JpaRepository<StudentParentEntity, Long> {
    Optional<StudentParentEntity> findByStudentIdentityId(Long studentId);
}
