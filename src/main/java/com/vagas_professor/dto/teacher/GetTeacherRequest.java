package com.vagas_professor.dto.teacher;

import jakarta.validation.constraints.NotBlank;

public record GetTeacherRequest (
    @NotBlank(message = "ID can not be blank")
    String id
) {}
