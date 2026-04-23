package com.example.rgpd.common.repository.identity;

import com.example.rgpd.common.entity.identity.StudentSchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentSchoolRepository extends JpaRepository<StudentSchoolEntity, Long> {
    List<StudentSchoolEntity> findAllByStudentIdentityId(Long studentId);
}
