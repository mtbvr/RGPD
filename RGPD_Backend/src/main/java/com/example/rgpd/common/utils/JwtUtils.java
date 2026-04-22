package com.example.rgpd.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final SecretKey SECRET = Keys.hmacShaKeyFor(
            "M1bWUrmuWy5kJ8rlbZsr2b1x1GttK5hYhH4wLJn5VRY".getBytes()
    );

    private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000; // 1 jour

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SECRET)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(SECRET)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}