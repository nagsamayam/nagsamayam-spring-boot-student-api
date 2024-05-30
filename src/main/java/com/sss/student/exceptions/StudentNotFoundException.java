package com.sss.student.exceptions;

import lombok.Getter;

@Getter
public class StudentNotFoundException extends RuntimeException {
    private final String errorCode;
    public StudentNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}

