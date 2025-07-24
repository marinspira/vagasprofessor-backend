package com.vagas_professor.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vagas_professor.dto.teacher.CreateTeacherRequest;
import com.vagas_professor.dto.teacher.GetTeacherRequest;
import com.vagas_professor.dto.teacher.TeacherResponse;
import com.vagas_professor.entity.Teacher;
import com.vagas_professor.entity.User;
import com.vagas_professor.repository.TeacherRepository;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherResponse getProfile(GetTeacherRequest request) {
        Teacher teacher = teacherRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

        String name = teacher.getFullName() != null && teacher.getFullName().contains(" ")
                ? teacher.getFullName().split(" ")[0]
                : teacher.getFullName();

        return new TeacherResponse(
                name,
                teacher.getFullName(),
                teacher.getPhone(),
                teacher.getCpf(),
                teacher.getDateOfBirth(),
                teacher.getCity(),
                teacher.getState(),
                teacher.getEducation(),
                teacher.getExperience(),
                teacher.getDegree(),
                teacher.getSkills(),
                teacher.getCvUrl(),
                teacher.getPhotoUrl(),
                teacher.getCreatedAt(),
                teacher.getUpdatedAt());
    }

    public TeacherResponse createProfile(CreateTeacherRequest request) {

        // Get current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new org.springframework.security.access.AccessDeniedException("User not authenticated");
        }

        User currentUser = (User) authentication.getPrincipal();

        // Check if user has ROLE_TEACHER
        if (!"ROLE_TEACHER".equals(currentUser.getRole())) {
            throw new AccessDeniedException("Only teachers can create a teacher profile");
        }

        // Check if the user already has a profile
        if (teacherRepository.findByUser(currentUser).isPresent()) {
            throw new IllegalStateException("Teacher profile already exists");
        }

        // Create teacher profile
        Teacher teacher = Teacher.builder()
                .user(currentUser)
                .fullName(request.fullName())
                .phone(request.phone())
                .cpf(request.cpf())
                .dateOfBirth(request.dateOfBirth())
                .city(request.city())
                .state(request.state())
                .education(request.education())
                .experience(request.experience())
                .degree(request.degree())
                .skills(request.skills())
                .cvUrl(request.cvUrl())
                .photoUrl(request.photoUrl())
                .build();

        teacher = teacherRepository.save(teacher);

        return TeacherResponse.builder()
                .name(teacher.getFullName().split(" ")[0])
                .fullName(teacher.getFullName())
                .phone(teacher.getPhone())
                .cpf(teacher.getCpf())
                .dateOfBirth(teacher.getDateOfBirth())
                .city(teacher.getCity())
                .state(teacher.getState())
                .education(teacher.getEducation())
                .experience(teacher.getExperience())
                .degree(teacher.getDegree())
                .skills(teacher.getSkills())
                .cvUrl(teacher.getCvUrl())
                .photoUrl(teacher.getPhotoUrl())
                .createdAt(teacher.getCreatedAt())
                .updatedAt(teacher.getUpdatedAt())
                .build();
    }
}
