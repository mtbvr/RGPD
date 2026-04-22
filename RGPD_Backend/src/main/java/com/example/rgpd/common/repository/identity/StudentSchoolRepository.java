package com.example.rgpd.common.repository.identity;

import com.example.rgpd.common.entity.identity.StudentSchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSchoolRepository extends JpaRepository<StudentSchoolEntity, Long> {
}
