package com.example.rgpd.common.dto.student;

import java.util.List;

public record LightStudent(
        Long id,
        String name,
        String firstname,
        String email,
        String phoneNumber,

        String parentName,
        String parentEmail,
        String parentPhone,

        List<SchoolInfo> school, // ✅ liste

        String eatingHabit
) {
    public record SchoolInfo(String notes, String appreciations, String options) {}
}