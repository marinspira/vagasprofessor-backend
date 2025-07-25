package com.vagas_professor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vagas_professor.dto.teacher.CreateTeacherRequest;
import com.vagas_professor.dto.teacher.GetTeacherRequest;
import com.vagas_professor.dto.teacher.TeacherResponse;
import com.vagas_professor.service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getProfile(@PathVariable String id) {
        TeacherResponse response = teacherService.getProfile(new GetTeacherRequest(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<TeacherResponse> createProfile(@Valid @RequestBody CreateTeacherRequest request) {
        TeacherResponse response = teacherService.createProfile(request);
        return ResponseEntity.ok(response);
    }
}
