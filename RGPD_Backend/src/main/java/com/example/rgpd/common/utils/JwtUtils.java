package com.example.rgpd.common.utils;

import com.example.rgpd.common.enums.RightsUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private final SecretKey SECRET = Keys.hmacShaKeyFor(
            "M1bWUrmuWy5kJ8rlbZsr2b1x1GttK5hYhH4wLJn5VRY".getBytes()
    );

    private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000;

    public String generateToken(String email, List<RightsUser> rights, String profile, Long studentIdentityId) {
        return Jwts.builder()
                .subject(email)
                .claim("rights", rights.stream().map(Enum::name).toList())
                .claim("profile", profile)
                .claim("studentId", studentIdentityId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SECRET)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long extractStudentId(String token) {
        Object val = extractAllClaims(token).get("studentId");
        if (val == null) return null;
        return ((Number) val).longValue();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRights(String token) {
        return (List<String>) extractAllClaims(token).get("rights");
    }

    public List<RightsUser> extractRightsEnum(String token) {
        return extractRights(token)
                .stream()
                .map(RightsUser::valueOf)
                .toList();
    }
}