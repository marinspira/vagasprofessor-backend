package com.vagas_professor.dto.teacher;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTeacherRequest(
        @NotBlank(message = "ID can not be blank") 
        String id,

        @NotBlank(message = "Name can not be blank") 
        String fullName,

        @NotBlank(message = "Phone number is required")
        String phone,

        @NotBlank(message = "CPF is required")
        String cpf,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "Date of birth is required")
        LocalDate dateOfBirth,

        @NotBlank(message = "City is required") 
        String city,
        
        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "Education is required") 
        String education,

        @NotBlank(message = "Experience is required") 
        String experience,

        @NotBlank(message = "Degree is required") 
        String degree,

        List<String> skills,

        String cvUrl,

        @NotBlank(message = "Photo is required") 
        String photoUrl
) {}