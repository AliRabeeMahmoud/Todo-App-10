package com.ali.java.todo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RoleValidator.class)
public @interface ValidateRole {

    public String message() default "Invalid. only allowed: USER, ADMIN";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
