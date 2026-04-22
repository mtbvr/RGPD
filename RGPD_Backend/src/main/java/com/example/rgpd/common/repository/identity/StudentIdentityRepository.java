package com.example.rgpd.common.repository.identity;

import com.example.rgpd.common.entity.identity.StudentIdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentIdentityRepository extends JpaRepository<StudentIdentityEntity, Long> {
}
