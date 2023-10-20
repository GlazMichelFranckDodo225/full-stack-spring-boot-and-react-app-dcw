package com.dgmf.service.impl;

import com.dgmf.dto.StudentDtoRequest;
import com.dgmf.dto.StudentDtoResponse;
import com.dgmf.exception.StudentAlreadyExistsException;
import com.dgmf.exception.StudentNotFoundException;
import com.dgmf.entity.Student;
import com.dgmf.repository.StudentRepository;
import com.dgmf.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public StudentDtoResponse createStudent(
            StudentDtoRequest studentDtoRequest) {
        // Check if the given Student already exists
        if(studentAlreadyExists(studentDtoRequest.getEmail())) {
            throw new StudentAlreadyExistsException(
                    "This Email is already in use");
        }

        // Convert StudentDtoRequest to Student
        Student student = mapDtoToStudent(studentDtoRequest);

        // Save Student
        Student savedStudent = studentRepository.save(student);

        // Convert savedStudent to StudentDtoResponse
        StudentDtoResponse studentDtoResponse = mapStudentToDto(savedStudent);

        return studentDtoResponse;
    }

    @Override
    public List<StudentDtoResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        // Convert List of Student to List of StudentDtoRequest
        List<StudentDtoResponse> studentDtoResponses = students.stream()
                .map(student -> mapStudentToDto(student))
                .collect(Collectors.toList());

        return studentDtoResponses;
    }

    @Override
    public StudentDtoResponse getStudentById(Long studentDtoRequestId) {
        Student student = studentRepository.findById(studentDtoRequestId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Sorry, No Student Found with the Id : "
                                + studentDtoRequestId
                ));

        StudentDtoResponse studentDtoResponse = mapStudentToDto(student);

        return studentDtoResponse;
    }

    @Override
    public StudentDtoResponse updateStudent(
            StudentDtoRequest studentDtoRequest, Long studentDtoRequestId) {
        Student studentFound = studentRepository.findById(studentDtoRequestId).map(
                student -> {
                    student.setFirstName(studentDtoRequest.getFirstName());
                    student.setLastName(studentDtoRequest.getLastName());
                    student.setEmail(studentDtoRequest.getEmail());
                    student.setDepartment(studentDtoRequest.getDepartment());

                    return student;
                }).orElseThrow(() -> new StudentNotFoundException(
                        "Sorry, Student Not Found !"));

        studentRepository.save(studentFound);

        return mapStudentToDto(studentFound);
    }

    @Override
    public void deleteStudent(Long studentDtoRequestId) {
        if(!studentRepository.existsById(studentDtoRequestId)) {
            throw new StudentNotFoundException("Sorry, Student Not Found !");
        }

        studentRepository.deleteById(studentDtoRequestId);
    }

    private StudentDtoResponse mapStudentToDto(Student student) {
        // Convert Student into StudentDtoResponse
        StudentDtoResponse studentDtoResponse = StudentDtoResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .department(student.getDepartment())
                .build();

        return studentDtoResponse;
    }

    private Student mapDtoToStudent(StudentDtoRequest studentDtoRequest) {
        // Convert StudentDtoRequest to Student
        Student student = Student.builder()
                .firstName(studentDtoRequest.getFirstName())
                .lastName(studentDtoRequest.getLastName())
                .email(studentDtoRequest.getEmail())
                .department(studentDtoRequest.getDepartment())
                .build();

        return student;
    }

    private boolean studentAlreadyExists(String studentEmail) {
        return studentRepository.findByEmail(studentEmail).isPresent();
    }
}
