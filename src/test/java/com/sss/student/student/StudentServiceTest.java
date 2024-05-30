package com.sss.student.student;

import com.sss.student.school.School;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    // Declare the dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        var studentDto = new StudentDto(
                "John",
                "Doe",
                "john@example.com",
                LocalDate.parse("2015-01-10"),
                1L);

        Student student = new Student(
                "John",
                "Doe",
                "john@example.com",
                LocalDate.parse("2015-01-10"));

        Student savedStudent = new Student(
                "John",
                "Doe",
                "john@example.com",
                LocalDate.parse("2015-01-10"));
        savedStudent.setId(1L);
        savedStudent.setUuid(UUID.randomUUID());

        // Mock the calls
        when(studentMapper.toStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        savedStudent.getId(),
                        savedStudent.getUuid(),
                        "John",
                        "Doe",
                        "john@example.com",
                        LocalDate.parse("2015-01-10"),
                        savedStudent.getCreatedAt(),
                        savedStudent.getUpdatedAt())
                );


        // When
        StudentResponseDto studentResponseDto = studentService.createStudent(studentDto);

        // Then
        assertEquals(studentDto.firstName(), studentResponseDto.firstName());
        assertEquals(studentDto.lastName(), studentResponseDto.lastName());
        assertEquals(studentDto.email(), studentResponseDto.email());

        verify(studentMapper, times(1)).toStudent(studentDto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void shouldReturnAllStudents() {
        // Given
        Student student = new Student(
                "John",
                "Doe",
                "john@example.com",
                LocalDate.parse("2015-01-10"));

        student.setId(1L);
        student.setUuid(UUID.randomUUID());

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        // Mock the calls
        when(studentRepository.findAll()).thenReturn(studentList);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        student.getId(),
                        student.getUuid(),
                        "John",
                        "Doe",
                        "john@example.com",
                        LocalDate.parse("2015-01-10"),
                        student.getCreatedAt(),
                        student.getUpdatedAt()));

        // When
        List<StudentResponseDto> studentResponseDtos = studentService.getAllStudents();

        // Then
        assertEquals(studentList.size(), studentResponseDtos.size());

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnStudentByUuid() {
        // Given
        UUID uuid = UUID.randomUUID();

        Student student = new Student(
                "John",
                "Doe",
                "john@example.com",
                LocalDate.parse("2015-01-10"));
        student.setId(1L);
        student.setUuid(uuid);

        // Mock the calls
        when(studentRepository.findOneByUuid(uuid)).thenReturn(student);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        1L,
                        uuid,
                        "John",
                        "Doe",
                        "john@example.com",
                        LocalDate.parse("2015-01-10"),
                        student.getCreatedAt(),
                        student.getCreatedAt()));

        // When
        StudentResponseDto studentResponseDto = studentService.getStudentByUuid(uuid);

        // Then
        assertEquals(studentResponseDto.uuid(), student.getUuid());
        assertEquals(studentResponseDto.firstName(), student.getFirstName());
        assertEquals(studentResponseDto.lastName(), student.getLastName());
        assertEquals(studentResponseDto.email(), student.getEmail());

        verify(studentRepository, times(1)).findOneByUuid(student.getUuid());

    }

    @Test
    public void shouldUpdateStudentByUuid() {
        // Given
        UUID uuid = UUID.randomUUID();

        School school = new School("St. Francis");
        school.setId(1L);

        Student student = new Student(
                "John",
                "Doe",
                "john@example.com",
                LocalDate.parse("2015-01-10"));
        student.setId(1L);
        student.setUuid(uuid);
        student.setSchool(school);

        StudentDto studentDto = new StudentDto(
                "Sachin",
                "Tendulkar",
                "sachin.tendulkar@gmail.com",
                LocalDate.parse("2014-01-10"),
                school.getId()
        );

        Student updatedStudent = new Student(
                studentDto.firstName(),
                studentDto.lastName(),
                studentDto.email(),
                studentDto.birthDate());
        updatedStudent.setId(1L);
        updatedStudent.setUuid(uuid);

        // Mock the calls
        when(studentRepository.findOneByUuid(uuid)).thenReturn(student);
        when(studentRepository.save(student))
                .thenReturn(updatedStudent);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        1L,
                        uuid,
                        "Sachin",
                        "Tendulkar",
                        "sachin.tendulkar@gmail.com",
                        LocalDate.parse("2014-01-10"),
                        updatedStudent.getCreatedAt(),
                        updatedStudent.getUpdatedAt()
                ));

        // When
        StudentResponseDto studentResponseDto = studentService.updateStudent(uuid, studentDto);

        // Then
        assertEquals(studentResponseDto.uuid(), updatedStudent.getUuid());
        assertEquals(studentResponseDto.firstName(), updatedStudent.getFirstName());
        assertEquals(studentResponseDto.lastName(), updatedStudent.getLastName());
        assertEquals(studentResponseDto.email(), updatedStudent.getEmail());
        assertEquals(studentResponseDto.birthDate(), updatedStudent.getBirthDate());

        verify(studentRepository, times(1)).findOneByUuid(uuid);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(updatedStudent);
    }

    @Test
    public void shouldFindStudentsByFirstName() {
        // Given
        String studentFirstName = "John";
        Student student = new Student(
                "John",
                "Doe",
                "john@example.com",
                LocalDate.parse("2015-01-10"));

        student.setId(1L);
        student.setUuid(UUID.randomUUID());

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        // Mock the calls
        when(studentRepository.findAllByFirstNameContaining(studentFirstName)).thenReturn(studentList);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(student.getId(),
                        student.getUuid(),
                        "John",
                        "Doe",
                        "john@example.com",
                        LocalDate.parse("2015-01-10"),
                        student.getCreatedAt(),
                        student.getUpdatedAt()));

        // When
        List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByFirstName(studentFirstName);

        // Then
        assertEquals(studentList.size(), studentResponseDtos.size());
        verify(studentRepository, times(1)).findAllByFirstNameContaining(studentFirstName);
    }

}