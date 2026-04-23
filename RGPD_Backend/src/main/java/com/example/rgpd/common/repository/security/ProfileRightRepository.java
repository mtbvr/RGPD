package com.example.rgpd.common.repository.security;

import com.example.rgpd.common.entity.security.ProfileRightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRightRepository extends JpaRepository<ProfileRightEntity, Long> {

    List<ProfileRightEntity> findByProfileId(Long profileId);

    @Query("SELECT pr FROM ProfileRightEntity pr JOIN FETCH pr.right WHERE pr.profile.id = :profileId")
    List<ProfileRightEntity> findByProfileIdWithRight(@Param("profileId") Long profileId);
}