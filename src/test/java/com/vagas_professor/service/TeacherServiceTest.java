package com.vagas_professor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vagas_professor.dto.teacher.GetTeacherRequest;
import com.vagas_professor.dto.teacher.TeacherResponse;
import com.vagas_professor.entity.Teacher;
import com.vagas_professor.repository.TeacherRepository;

public class TeacherServiceTest {
    private TeacherRepository teacherRepository;
    private TeacherService teacherService;

    @BeforeEach // is used to signal that the annotated method should be executed before each
                // @Test, @RepeatedTest...
    void setUp() {
        teacherRepository = mock(TeacherRepository.class);
        teacherService = new TeacherService(teacherRepository);
    }

    @Test
    void shouldReturnTeacherProfileWhenIdExists() {
        // Arrange
        String teacherId = "e6d2d19c-ef12-4d1c-bd62-3cba9e8c2a1f";
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setFullName("Maria Ferreira");
        teacher.setPhone("1234567890");
        teacher.setCpf("123.456.789-00");
        teacher.setDateOfBirth(LocalDate.of(1990, 5, 15));
        teacher.setCity("São Paulo");
        teacher.setState("SP");
        teacher.setEducation("Bachelor's Degree in Education");
        teacher.setExperience("5 years of teaching");
        teacher.setDegree("B.Ed");
        teacher.setSkills(List.of("Math", "Science"));
        teacher.setCvUrl("http://example.com/cv.pdf");
        teacher.setPhotoUrl("http://example.com/photo.jpg");
        teacher.setCreatedAt(LocalDateTime.of(2023, 1, 1, 10, 0));
        teacher.setUpdatedAt(LocalDateTime.of(2024, 1, 1, 10, 0));

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

        // Act
        GetTeacherRequest request = new GetTeacherRequest(teacherId);
        TeacherResponse result = teacherService.getProfile(request);

        // Assert
        assertEquals("Maria Ferreira", result.fullName());
        assertEquals("1234567890", result.phone());
        assertEquals("123.456.789-00", result.cpf());
        assertEquals(LocalDate.of(1990, 5, 15), result.dateOfBirth());
        assertEquals("São Paulo", result.city());
        assertEquals("SP", result.state());
        assertEquals("Bachelor's Degree in Education", result.education());
        assertEquals("5 years of teaching", result.experience());
        assertEquals("B.Ed", result.degree());
        assertEquals(List.of("Math", "Science"), result.skills());
        assertEquals("http://example.com/cv.pdf", result.cvUrl());
        assertEquals("http://example.com/photo.jpg", result.photoUrl());
        assertEquals(LocalDateTime.of(2023, 1, 1, 10, 0), result.createdAt());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), result.updatedAt());
    }

    @Test
    void shouldThrowWhenTeacherNotFound() {
        String teacherId = "e6d2d19c-ef12-4d1c-bd62-3cba9e8c2a1f";

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherService.getProfile(new GetTeacherRequest(teacherId));
        });

        assertEquals("Teacher not found", exception.getMessage());
    }

    
}
