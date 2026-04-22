package com.example.rgpd.common.repository.security;

import com.example.rgpd.common.entity.security.ProfileRightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRightRepository extends JpaRepository<ProfileRightEntity, Long> {
    List<ProfileRightEntity> findByProfileId(Long profileId);
}
