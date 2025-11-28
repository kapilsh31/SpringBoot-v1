package com.learning.spring.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private LocalDate dateOfJoining;
    private boolean statusActive;

}
