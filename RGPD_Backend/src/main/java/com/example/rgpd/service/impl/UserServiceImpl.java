package com.example.rgpd.service.impl;

import com.example.rgpd.common.dto.auth.AuthRequest;
import com.example.rgpd.common.dto.auth.AuthResponse;
import com.example.rgpd.common.entity.security.ProfileEntity;
import com.example.rgpd.common.entity.security.ProfileUserEntity;
import com.example.rgpd.common.entity.security.UserEntity;
import com.example.rgpd.common.enums.RightsUser;
import com.example.rgpd.common.repository.security.ProfileRepository;
import com.example.rgpd.common.repository.security.ProfileRightRepository;
import com.example.rgpd.common.repository.security.ProfileUserRepository;
import com.example.rgpd.common.repository.security.UserRepository;
import com.example.rgpd.common.utils.JwtUtils;
import com.example.rgpd.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final ProfileUserRepository profileUserRepository;
    private final ProfileRightRepository profileRightRepository;

    @Override
    @Transactional("securityTransactionManager")
    public AuthResponse signup(AuthRequest request) {

        if (userRepository.findByLogin(request.login()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        ProfileEntity defaultProfile = profileRepository.findByName("STAFF")
                .orElseThrow(() -> new RuntimeException("Default profile missing"));

        UserEntity user = new UserEntity();
        user.setLogin(request.login());
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        ProfileUserEntity profileUser = new ProfileUserEntity();
        profileUser.setUser(user);
        profileUser.setProfile(defaultProfile);
        profileUserRepository.save(profileUser);

        List<RightsUser> rights = profileRightRepository
                .findByProfileIdWithRight(defaultProfile.getId())
                .stream()
                .map(link -> RightsUser.valueOf(link.getRight().getName()))
                .toList();

        String token = jwtUtils.generateToken(
                user.getLogin(),
                rights,
                defaultProfile.getName(),
                user.getStudentIdentityId() // null pour STAFF
        );

        return new AuthResponse(token);
    }

    @Override
    @Transactional("securityTransactionManager")
    public AuthResponse login(AuthRequest request) {

        UserEntity user = userRepository.findByLogin(request.login())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        List<ProfileUserEntity> profilesUser = profileUserRepository.findByUserId(user.getId());

        List<RightsUser> rights = profilesUser.stream()
                .flatMap(pu -> profileRightRepository.findByProfileIdWithRight(pu.getProfile().getId()).stream())
                .map(link -> RightsUser.valueOf(link.getRight().getName()))
                .distinct()
                .toList();

        String profileName = profilesUser.isEmpty() ? "STAFF" : profilesUser.get(0).getProfile().getName();

        String token = jwtUtils.generateToken(
                user.getLogin(),
                rights,
                profileName,
                user.getStudentIdentityId()
        );

        return new AuthResponse(token);
    }
}
