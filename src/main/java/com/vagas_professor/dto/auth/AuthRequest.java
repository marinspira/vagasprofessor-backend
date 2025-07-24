package com.vagas_professor.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @Schema(description = "Username", example = "testeando", required = true)
        @NotBlank(message = "Username is required") 
        String username,

        @Schema(description = "Email", example = "maria@gmail.com", required = true)
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @Schema(description = "Password", example = "123456789", required = true)
        @NotBlank(message = "Password is required") 
        String password) {
}