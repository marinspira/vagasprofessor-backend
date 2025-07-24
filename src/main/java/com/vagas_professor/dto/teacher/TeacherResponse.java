package com.vagas_professor.dto.teacher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record TeacherResponse(
        String name,
        String fullName,
        String phone,
        String cpf,
        LocalDate dateOfBirth,

        String city,
        String state,

        String education,
        String experience,
        String degree,

        List<String> skills,

        String cvUrl,
        String photoUrl,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
 ) {}