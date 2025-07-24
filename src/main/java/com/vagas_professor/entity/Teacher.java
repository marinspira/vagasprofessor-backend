package com.vagas_professor.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@Builder
@Data
@NoArgsConstructor // Lombok: generates a no-argument constructor
@AllArgsConstructor // Lombok: generates a constructor with all fields
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String fullName;
    private String phone;
    private String cpf;
    private LocalDate dateOfBirth;

    private String city;
    private String state;

    @Lob
    private String education;
    @Lob
    private String experience;
    private String degree;

    @ElementCollection
    private List<String> skills;

    private String cvUrl;
    private String photoUrl;

    // Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // JPA: this method runs before the entity is first saved
    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate // JPA: this method runs before any update operation
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
