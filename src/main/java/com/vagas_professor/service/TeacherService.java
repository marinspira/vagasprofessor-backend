package com.vagas_professor.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vagas_professor.dto.teacher.CreateTeacherRequest;
import com.vagas_professor.dto.teacher.GetTeacherRequest;
import com.vagas_professor.dto.teacher.TeacherResponse;
import com.vagas_professor.entity.Teacher;
import com.vagas_professor.entity.User;
import com.vagas_professor.repository.TeacherRepository;
import com.vagas_professor.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class TeacherService {
        private TeacherRepository teacherRepository;
        private UserRepository userRepository;

        public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository) {
                this.teacherRepository = teacherRepository;
                this.userRepository = userRepository;
        }

        public TeacherResponse getProfile(GetTeacherRequest request) {
                Teacher teacher = teacherRepository.findById(request.id())
                                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

                String name = teacher.getFullName() != null && teacher.getFullName().contains(" ")
                                ? teacher.getFullName().split(" ")[0]
                                : teacher.getFullName();

                return new TeacherResponse(
                                name,
                                teacher.getFullName(),
                                teacher.getPhone(),
                                teacher.getCpf(),
                                teacher.getDateOfBirth(),
                                teacher.getCity(),
                                teacher.getState(),
                                teacher.getEducation(),
                                teacher.getExperience(),
                                teacher.getDegree(),
                                teacher.getSkills(),
                                teacher.getCvUrl(),
                                teacher.getPhotoUrl(),
                                teacher.getCreatedAt(),
                                teacher.getUpdatedAt());
        }

        public TeacherResponse createProfile(CreateTeacherRequest request) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName(); // This is the username from login

                User currentUser = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                // Check if user has ROLE_TEACHER
                if (!"ROLE_TEACHER".equals("ROLE_" + currentUser.getRole())) {
                        throw new AccessDeniedException("Only teachers can create a teacher profile");
                }

                // Check if the user already has a profile
                try {
                        teacherRepository.findByUser(currentUser).ifPresent(t -> {
                                throw new IllegalStateException("Teacher profile already exists");
                        });
                } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                }

                Teacher teacher = Teacher.builder()
                                .user(currentUser)
                                .fullName(request.fullName())
                                .phone(request.phone())
                                .cpf(request.cpf())
                                .dateOfBirth(request.dateOfBirth())
                                .city(request.city())
                                .state(request.state())
                                .education(request.education())
                                .experience(request.experience())
                                .degree(request.degree())
                                .skills(request.skills())
                                .cvUrl(request.cvUrl())
                                .photoUrl(request.photoUrl())
                                .build();

                teacher = teacherRepository.save(teacher);

                String name = teacher.getFullName() != null && teacher.getFullName().contains(" ")
                                ? teacher.getFullName().split(" ")[0]
                                : teacher.getFullName();

                return new TeacherResponse(
                                name,
                                teacher.getFullName(),
                                teacher.getPhone(),
                                teacher.getCpf(),
                                teacher.getDateOfBirth(),
                                teacher.getCity(),
                                teacher.getState(),
                                teacher.getEducation(),
                                teacher.getExperience(),
                                teacher.getDegree(),
                                teacher.getSkills(),
                                teacher.getCvUrl(),
                                teacher.getPhotoUrl(),
                                teacher.getCreatedAt(),
                                teacher.getUpdatedAt());
        }
}
