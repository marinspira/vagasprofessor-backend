package com.vagas_professor.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vagas_professor.dto.auth.AuthRequest;
import com.vagas_professor.dto.auth.AuthResponse;
import com.vagas_professor.dto.auth.RegisterRequest;
import com.vagas_professor.entity.User;
import com.vagas_professor.repository.UserRepository;
import com.vagas_professor.security.JwtService;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    // constructor
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        // atribute param to private variable
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
        this.jwtService = jwtService;

    }

    public AuthResponse register(RegisterRequest request) {
        // 1. Check if username/email already exis
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Username already taken");
        }

        // Check if email already exists
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // create User entity
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(encoder.encode(request.password()));
        user.setRole(request.role());

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        // Retrieve user by username or email
        User user = userRepository.findByUsername(request.username())
            .or(() -> userRepository.findByEmail(request.username()))
            .orElseThrow(() -> new IllegalArgumentException("User not found with that username or email"));

        // 2. Check password matches
        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // 3. Generate JWT token
        String token = jwtService.generateToken(user.getUsername());

        // 4. Return token wrapped in response DTO
        return new AuthResponse(token);
    }
}
