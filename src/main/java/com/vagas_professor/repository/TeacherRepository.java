package com.vagas_professor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vagas_professor.entity.Teacher;
import com.vagas_professor.entity.User;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher>findById(String id);
    Optional<Teacher>findByUser(User user);
}
