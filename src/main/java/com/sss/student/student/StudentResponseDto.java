package com.sss.student.student;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public record StudentResponseDto(
        UUID uuid,
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,
        String email,

        @JsonProperty("birth_date")
        LocalDate birthDate
) {
}
