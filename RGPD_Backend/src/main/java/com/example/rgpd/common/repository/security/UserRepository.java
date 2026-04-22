package com.example.rgpd.common.repository.security;

import com.example.rgpd.common.entity.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
