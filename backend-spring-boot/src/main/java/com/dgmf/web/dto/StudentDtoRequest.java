package com.dgmf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentDtoRequest {
    // private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String department;
}
