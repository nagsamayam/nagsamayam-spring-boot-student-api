package com.sss.student;

import com.sss.student.student.Student;
import com.sss.student.student.StudentRepository;
import com.sss.student.student.StudentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentApplication {

	private final StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	// @Bean // You can uncomment to execute below code
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("------ Hello world----");
//			int updatedRecords = studentRepository.updateStudentBirthDate(LocalDate.now(), 1L);
//			System.out.println("Updated records: " + updatedRecords);
//			studentRepository.findByEmailNamedQuery("nag.samayam@gmail.com")
//					.forEach(student -> System.out.println(student.getUuid()));

			Specification<Student> specification = Specification
					.where(StudentSpecifications.hasEmail("test@example.com"))
					.and(StudentSpecifications.firstNameLike("test"));

			studentRepository
					.findAll(specification)
					.forEach(student -> System.out.println(student.getUuid()));
		};
	}

}
