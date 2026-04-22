package com.example.rgpd.common.repository.security;

import com.example.rgpd.common.entity.security.ProfileEntity;
import com.example.rgpd.common.entity.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByName(String login);
}
