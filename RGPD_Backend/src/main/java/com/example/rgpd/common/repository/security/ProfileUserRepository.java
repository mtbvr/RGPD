package com.example.rgpd.common.repository.security;

import com.example.rgpd.common.entity.security.ProfileRightEntity;
import com.example.rgpd.common.entity.security.ProfileUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileUserRepository extends JpaRepository<ProfileUserEntity, Long> {
    List<ProfileUserEntity> findByUserId(Long userId);
}
