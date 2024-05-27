package com.sss.student.validation;

import com.sss.student.student.StudentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final StudentService studentService;

    public UniqueEmailValidator(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !studentService.checkEmailExists(email);
    }
}
