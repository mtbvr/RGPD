package com.example.rgpd.common.repository.security;

import com.example.rgpd.common.entity.security.ProfileUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileUserRepository extends JpaRepository<ProfileUserEntity, Long> {
}
