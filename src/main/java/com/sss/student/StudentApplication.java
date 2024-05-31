package com.sss.student;

import com.sss.student.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			int updatedRecords = studentRepository.updateStudentBirthDate(LocalDate.now(), 1L);
			System.out.println("Updated records: " + updatedRecords);
			studentRepository.findByEmailNamedQuery("nag.samayam@gmail.com")
					.forEach(student -> System.out.println(student.getUuid()));
		};
	}

}
