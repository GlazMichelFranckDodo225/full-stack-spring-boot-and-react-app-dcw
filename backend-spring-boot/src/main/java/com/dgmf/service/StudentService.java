package com.dgmf.service;

import com.dgmf.dto.StudentDtoRequest;
import com.dgmf.dto.StudentDtoResponse;

import java.util.List;

public interface StudentService {
    StudentDtoResponse createStudent(StudentDtoRequest studentDtoRequest);
    List<StudentDtoResponse> getAllStudents();
    StudentDtoResponse getStudentById(Long studentDtoRequestId);
    StudentDtoResponse updateStudent(
            StudentDtoRequest studentDtoRequest, Long studentDtoRequestId);
    void deleteStudent(Long studentDtoRequestId);
}
