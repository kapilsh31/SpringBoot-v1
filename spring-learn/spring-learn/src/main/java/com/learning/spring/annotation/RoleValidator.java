package com.learning.spring.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;

public class RoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //String s -- passes by the user
        HashSet<String> roles = new HashSet<>();
        roles.add("ADMIN");
        roles.add("USER");

        return roles.contains(s);
    }
}
