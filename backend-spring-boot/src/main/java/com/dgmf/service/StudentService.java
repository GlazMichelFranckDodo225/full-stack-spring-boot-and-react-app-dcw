package com.dgmf.service;

import com.dgmf.web.dto.StudentDtoRequest;
import com.dgmf.web.dto.StudentDtoResponse;

import java.util.List;

public interface StudentService {
    StudentDtoResponse createStudent(StudentDtoRequest studentDtoRequest);
    List<StudentDtoResponse> getAllStudents();
    StudentDtoResponse getStudentById(Long studentDtoRequestId);
    StudentDtoResponse updateStudent(
            StudentDtoRequest studentDtoRequest, Long studentDtoRequestId);
    String deleteStudent(Long studentDtoRequestId);
}
