package com.vagas_professor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vagas_professor.dto.auth.AuthRequest;
import com.vagas_professor.dto.auth.AuthResponse;
import com.vagas_professor.dto.auth.RegisterRequest;
import com.vagas_professor.entity.User;
import com.vagas_professor.repository.UserRepository;
import com.vagas_professor.security.JwtService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class AuthServiceTest {
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private JwtService jwtService;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        encoder = mock(PasswordEncoder.class);
        jwtService = mock(JwtService.class);
        authService = new AuthService(userRepository, encoder, jwtService);
    }

    @Test
    void shoulThrowWhenUsernameAlreadyExists() {
        RegisterRequest request = new RegisterRequest("maria@gmail.com", "marinspira", "password", "TEACHER");

        when(userRepository.findByUsername("marinspira"))
                // Option means that it may or may not contain a non-null value
                .thenReturn(Optional.of(new User()));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authService.register(request);
        });

        assertEquals("Username already taken", exception.getMessage());
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        RegisterRequest request = new RegisterRequest("maria@gmail.com", "marinspira", "password", "TEACHER");

        when(userRepository.findByUsername("marinspira")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("maria@gmail.com")).thenReturn(Optional.empty());
        when(encoder.encode("password")).thenReturn("hashed");
        when(jwtService.generateToken("marinspira")).thenReturn("fake-jwt");

        var response = authService.register(request);

        assertEquals("fake-jwt", response.token());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldLoginSuccessfully() {
        AuthRequest request = new AuthRequest("marinspira", "maria@gmail.com", "password");

        User user = new User();
        user.setUsername("marinspira");
        user.setPassword("hashed-password");

        when(userRepository.findByUsername("marinspira")).thenReturn(Optional.of(user));
        when(encoder.matches("password", "hashed-password")).thenReturn(true);
        when(jwtService.generateToken("marinspira")).thenReturn("valid-jwt");

        AuthResponse response = authService.login(request);

        assertEquals("valid-jwt", response.token());
    }

    // @Test
    // void shouldThrowWhenUserNotFound() {
    // AuthRequest request = new AuthRequest("not_found", null, "password");

    // when(userRepository.findByUsername("not_found")).thenReturn(Optional.empty());

    // Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    // authService.login(request);
    // });

    // assertEquals("Invalid credentials", exception.getMessage());
    // }

    // @Test
    // void shouldThrowWhenPasswordDoesNotMatch() {
    // AuthRequest request = new AuthRequest("marinspira", null, "wrong-password");

    // User user = new User();
    // user.setUsername("marinspira");
    // user.setPassword("hashed-password");

    // when(userRepository.findByUsername("marinspira")).thenReturn(Optional.of(user));
    // when(encoder.matches("wrong-password", "hashed-password")).thenReturn(false);

    // Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    // authService.login(request);
    // });

    // assertEquals("Invalid credentials", exception.getMessage());
    // }
}
