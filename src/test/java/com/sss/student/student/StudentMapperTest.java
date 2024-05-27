package com.sss.student.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        // Given
       var studentDto = new StudentDto(
               "Sachin",
               "Tendulkar",
               "sachin.tendulkar@gmail.com",
               LocalDate.parse("2015-01-10"),
               1L);

       // When
       Student student = studentMapper.toStudent(studentDto);

       // Then
       assertEquals(studentDto.firstName(), student.getFirstName());
       assertEquals(studentDto.email(), student.getEmail());
       assertEquals(studentDto.birthDate(), student.getBirthDate());
       assertNotNull(student.getSchool());
       assertEquals(studentDto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_student_dto_is_null() {
        var e = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("The student dto is null", e.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        // Given
        Student student = new Student(
                "Sachin",
                "Tendulkar",
                "sachin.tendulkar@gmail.com",
                LocalDate.parse("2015-01-02"));

        // When
        StudentResponseDto studentResponseDto = studentMapper.toStudentResponseDto(student);

        // Then
        assertEquals(studentResponseDto.uuid(), student.getUuid());
        assertEquals(studentResponseDto.firstName(), student.getFirstName());
        assertEquals(studentResponseDto.lastName(), student.getLastName());
        assertEquals(studentResponseDto.email(), student.getEmail());
    }
}