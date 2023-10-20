package com.dgmf.web.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentDtoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
}
