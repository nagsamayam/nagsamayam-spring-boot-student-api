package com.sss.student.student;

import com.sss.student.utils.string.CamelToSnakeCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<StudentResponseDto> index() {
        return studentService.getAllStudents();
    }

    @PostMapping("")
    public StudentResponseDto store(@Valid @RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<StudentResponseDto> show(@PathVariable UUID uuid) {
        StudentResponseDto student = studentService.getStudentByUuid(uuid);
       if(student != null) {
           return new ResponseEntity<>(student, HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{uuid}")
    public StudentResponseDto edit(@PathVariable UUID uuid, @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(uuid, studentDto);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable UUID uuid) {
        studentService.deleteStudentByUuid(uuid);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search-by-first-name/{firstName}")
    public List<StudentResponseDto> searchByFirstName(@PathVariable String firstName) {
        return studentService.getStudentsByFirstName(firstName);
    }

    @GetMapping("/search-by-email/{email}")
    public List<StudentResponseDto> searchByEmail(@PathVariable String email) {
        return studentService.getStudentsByEmail(email);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = new HashMap<String, String>();

        e.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = CamelToSnakeCase.camelToSnake(((FieldError) error).getField());
            var errorMsg = error.getDefaultMessage();
            errors.put(fieldName, errorMsg);
        });

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
