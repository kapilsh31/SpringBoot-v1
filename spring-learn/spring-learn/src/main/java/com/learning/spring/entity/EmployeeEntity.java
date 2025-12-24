package com.learning.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

    private Integer age;

    @Column(nullable = false)
    private LocalDate dateOfJoining;

    @Column(nullable = false)
    private BigDecimal salary;

    private boolean statusActive;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate creationDate;
}
