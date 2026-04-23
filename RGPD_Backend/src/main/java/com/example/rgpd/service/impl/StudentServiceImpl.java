package com.example.rgpd.service.impl;

import com.example.rgpd.common.dto.student.LightStudent;
import com.example.rgpd.common.entity.identity.StudentIdentityEntity;
import com.example.rgpd.common.repository.identity.StudentIdentityRepository;
import com.example.rgpd.common.repository.identity.StudentParentRepository;
import com.example.rgpd.common.repository.identity.StudentSchoolLifeRepository;
import com.example.rgpd.common.repository.identity.StudentSchoolRepository;
import com.example.rgpd.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentIdentityRepository repository;
    private final StudentParentRepository parentRepository;
    private final StudentSchoolRepository schoolRepository;
    private final StudentSchoolLifeRepository schoolLifeRepository;

    @Override
    public List<LightStudent> findAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public LightStudent findById(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isStudent = auth.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("RECTIFY_DATA"));

        if (isStudent && auth.getCredentials() != null) {
            Long myStudentId = (Long) auth.getCredentials();
            if (!myStudentId.equals(id)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }

        return toDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found")));
    }

    @Override
    public LightStudent create(LightStudent dto) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("STUDENT_CREATE user={}", user);

        StudentIdentityEntity entity = new StudentIdentityEntity();
        entity.setName(dto.name());
        entity.setFirstname(dto.firstname());
        entity.setEmail(dto.email());
        entity.setPhoneNumber(dto.phoneNumber());

        repository.save(entity);

        return toDto(entity);
    }

    @Override
    public LightStudent update(Long id, LightStudent dto) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        log.warn("STUDENT_UPDATE user={} id={}", user, id);

        StudentIdentityEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        entity.setName(dto.name());
        entity.setFirstname(dto.firstname());
        entity.setEmail(dto.email());
        entity.setPhoneNumber(dto.phoneNumber());

        repository.save(entity);

        return toDto(entity);
    }

    @Override
    public void delete(Long id) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        log.warn("STUDENT_DELETE user={} id={}", user, id);

        repository.deleteById(id); // ici ok (pas sensible)
    }

    private LightStudent toDto(StudentIdentityEntity entity) {
        var parent = parentRepository.findByStudentIdentityId(entity.getId()).orElse(null);
        var life   = schoolLifeRepository.findByStudentIdentityId(entity.getId()).orElse(null);
        var school = schoolRepository.findAllByStudentIdentityId(entity.getId());

        return new LightStudent(
                entity.getId(),
                entity.getName(),
                entity.getFirstname(),
                entity.getEmail(),
                entity.getPhoneNumber(),

                parent != null ? parent.getName() : null,
                parent != null ? parent.getEmail() : null,
                parent != null ? parent.getPhoneNumber() : null,

                school.stream()
                        .map(s -> new LightStudent.SchoolInfo(s.getNotes(), s.getAppreciations(), s.getOptions()))
                        .toList(), // ✅

                life != null ? life.getEatingHabit() : null
        );
    }
}