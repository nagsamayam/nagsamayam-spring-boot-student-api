package com.sss.student.student;

import com.sss.student.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(StudentDto studentDto) {
        if(studentDto == null) {
            throw new NullPointerException("The student dto is null");
        }
        var student = new Student(
                studentDto.firstName(),
                studentDto.lastName(),
                studentDto.email().toLowerCase(),
                studentDto.birthDate());

        var school = new School();
        school.setId(studentDto.schoolId());
        student.setSchool(school);

        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(
                student.getUuid(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getBirthDate());

    }
}
