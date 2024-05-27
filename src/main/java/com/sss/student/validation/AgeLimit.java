package com.sss.student.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeLimitValidator.class)
public @interface AgeLimit{
    int minimumAge() default 6;
    String message() default "Student must be at least 6 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}