package com.learning.spring.dto;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;

    @NotEmpty(message = "{NAME_NOT_NULL}")
    @Size(min = 3 , max = 30, message = "{NAME_SIZE}")
    private String name;

    @Min(value = 22, message = "{MIN_AGE}")
    @Max(value = 65 , message = "{MAX_AGE}")
    private Integer age;

    @Email(message = "{EMAIL_VALID}")
    private String email;

    @NotBlank(message = "{PHONE_MANDATORY}")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "{PHONE_VALID}")
    private String phone;

    @PastOrPresent(message = "{DOJ_VALID}")
    private LocalDate dateOfJoining;

    @NotNull(message = "{SALARY_NULL}")
    @Digits(integer = 7, fraction = 2, message = "{SALARY_VALID}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{SALARY_NOT_ZERO}")
    private Double salary;

    private boolean statusActive;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private  String createdBy;

    private String lastModifiedBy;

}
