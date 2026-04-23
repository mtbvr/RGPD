package com.example.rgpd.service;

import com.example.rgpd.common.dto.student.LightStudent;

import java.util.List;

public interface StudentService {

    List<LightStudent> findAll();

    LightStudent findById(Long id);

    LightStudent create(LightStudent dto);

    LightStudent update(Long id, LightStudent dto);

    void delete(Long id);
}