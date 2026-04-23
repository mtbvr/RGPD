package com.example.rgpd.controller;

import com.example.rgpd.common.dto.student.LightStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rgpd.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // =========================
    // GET ALL
    // =========================
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ACCESS_DATA')")
    public List<LightStudent> findAll() {
        return studentService.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACCESS_DATA')")
    public LightStudent findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    // =========================
    // CREATE
    // =========================
    @PostMapping
    @PreAuthorize("hasAnyAuthority('RECTIFY_DATA')")
    public LightStudent create(@RequestBody LightStudent dto) {
        return studentService.create(dto);
    }

    // =========================
    // UPDATE (rectification RGPD)
    // =========================
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('RECTIFY_DATA')")
    public LightStudent update(@PathVariable Long id, @RequestBody LightStudent dto) {
        return studentService.update(id, dto);
    }

    // =========================
    // DELETE (RGPD)
    // =========================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_DATA')")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}