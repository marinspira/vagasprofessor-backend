package com.vagas_professor.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// Record is a special class projected to be a transparent "container" for imatables data
public record RegisterRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Username is required")
        String username,
        
        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Role is required")
        @Pattern(regexp = "SCHOOL|TEACHER", message = "Role must be either SCHOOL or TEACHER")
        String role
        ) {}
