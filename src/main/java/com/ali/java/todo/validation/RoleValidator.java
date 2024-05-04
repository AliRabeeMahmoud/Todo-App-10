package com.ali.java.todo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class RoleValidator implements ConstraintValidator<ValidateRole, String> {
    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        List<String> ageTypes = Arrays.asList("ADMIN", "USER","");
        return ageTypes.contains(role);
    }
}
