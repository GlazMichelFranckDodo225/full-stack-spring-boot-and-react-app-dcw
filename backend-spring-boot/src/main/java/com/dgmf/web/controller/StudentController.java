package com.dgmf.web.controller;

import com.dgmf.web.dto.StudentDtoRequest;
import com.dgmf.web.dto.StudentDtoResponse;
import com.dgmf.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDtoResponse>> getStudents() {
        return new ResponseEntity<>(
                studentService.getAllStudents(),
                HttpStatus.FOUND
        );
    }

    @PostMapping
    public ResponseEntity<StudentDtoResponse> addStudent(
            @RequestBody StudentDtoRequest studentDtoRequest
    ) {
        return ResponseEntity
                .ok(studentService.createStudent(studentDtoRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDtoResponse> updateStudent(
            @RequestBody StudentDtoRequest studentDtoRequest,
            @PathVariable("id") Long studentDtoRequestId
    ) {
        return new ResponseEntity<>(
                studentService.updateStudent(
                        studentDtoRequest,
                        studentDtoRequestId
                ),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDtoResponse> getStudentById(
            @PathVariable("id") Long studentDtoRequestId) {
        return ResponseEntity
                .ok(studentService.getStudentById(studentDtoRequestId));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long studentDtoRequestId) {
        return studentService.deleteStudent(studentDtoRequestId);
    }
}
