package com.example.rgpd.common.repository.security;

import com.example.rgpd.common.entity.security.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
}
