package com.sss.student.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueEmail {
    String message() default "Email should be unique. Please choose another one";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
