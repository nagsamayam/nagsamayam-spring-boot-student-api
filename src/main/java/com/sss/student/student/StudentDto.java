package com.sss.student.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sss.student.validation.AgeLimit;
import com.sss.student.validation.UniqueEmail;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record StudentDto(
        @JsonProperty("first_name")
        @NotBlank(message = "First name can not be blank")
        @Size(min = 3, max = 50)
        String firstName,
        @JsonProperty("last_name")
        @NotBlank(message = "Last name can not be blank")
        @Size(max = 20)
        String lastName,

        @NotBlank(message = "Email can not be blank")
        @Email(message = "Email is invalid", flags = {Pattern.Flag.CASE_INSENSITIVE})
        @UniqueEmail
        String email,

        @JsonProperty("birth_date")
        @NotNull(message = "Birth date can not be blank")
        @AgeLimit(minimumAge = 6, message = "You are too young to join the school")
        LocalDate birthDate,

        @JsonProperty("school_id")
        @NotNull(message = "School ID can not be blank")
        Long schoolId
){
}
