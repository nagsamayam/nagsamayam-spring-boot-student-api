package com.sss.student;

import com.sss.student.school.School;
import com.sss.student.student.Student;
import com.sss.student.student.StudentRepository;
import com.sss.student.student.StudentSpecifications;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
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

			// Generate fake data
			for(int i = 1; i < 11; i++) {
				Faker faker = new Faker();
				String firstName = faker.name().firstName();
				String lastName = faker.name().lastName();
				String email = faker.internet().safeEmailAddress();
				LocalDate birthDate = LocalDate.now();

				School school = new School();
				school.setId(1L);

				Student student = Student
						.builder()
						.firstName(firstName)
						.lastName(lastName)
						.email(email)
						.birthDate(birthDate)
						.school(school)
						.build();

				studentRepository.save(student);
			}

			Specification<Student> specification = Specification
					.where(StudentSpecifications.hasEmail("test@example.com"))
					.and(StudentSpecifications.firstNameLike("test"));

			studentRepository
					.findAll(specification)
					.forEach(student -> System.out.println(student.getUuid()));
		};
	}

}
