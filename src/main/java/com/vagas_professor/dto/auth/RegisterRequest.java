package com.vagas_professor.dto.auth;

// Record is a special class projected to be a transparent "container" for imatables data
public record RegisterRequest(String email, String username, String password, String role) {}
