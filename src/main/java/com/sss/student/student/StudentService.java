package com.sss.student.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toStudentResponseDto).collect(Collectors.toList());
    }

    public StudentResponseDto createStudent(StudentDto studentDto) {
        Student student = studentMapper.toStudent(studentDto);
        var savedStudent = studentRepository.save(student);

        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public StudentResponseDto getStudentByUuid(UUID uuid) {
        Student student = studentRepository.findOneByUuid(uuid);
        return studentMapper.toStudentResponseDto(student);
    }

    public Boolean checkEmailExists(String email) {
        Student student = studentRepository.findOneByEmail(email);
        if (student == null) {
            return false;
        }
        return student.getEmail().equals(email.toLowerCase());
    }

    public void deleteStudentByUuid(UUID uuid) {
        studentRepository.deleteByUuid(uuid);
    }

    public StudentResponseDto updateStudent(UUID uuid, StudentDto studentDto) {
        Student student = studentRepository.findOneByUuid(uuid);
        if (student == null) {
            return new StudentResponseDto(null, null, null, null, null);
        }

        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());
        student.setBirthDate(studentDto.birthDate());

        var savedStudent = studentRepository.save(student);

        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> getStudentsByFirstName(String email) {
        return studentRepository.findAllByFirstNameContaining(email).stream().map(studentMapper::toStudentResponseDto).collect(Collectors.toList());
    }

    public List<StudentResponseDto> getStudentsByEmail(String email) {
        return studentRepository.findAllByEmailContaining(email).stream().map(studentMapper::toStudentResponseDto).collect(Collectors.toList());
    }
}
