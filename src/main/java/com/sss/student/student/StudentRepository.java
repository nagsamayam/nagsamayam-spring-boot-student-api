package com.sss.student.student;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findOneByUuid(UUID uuid);
    Student findOneByEmail(String email);
    List<Student> findAllByFirstNameContaining(String firstName);
    List<Student> findAllByEmailContaining(String email);

    @Transactional
    void deleteByUuid(UUID uuid);

    String FILTER_STUDENTS_ON_FIRST_NAME_AND_LAST_NAME_QUERY = "select s from Student s where s.firstName like CONCAT('%',:firstName,'%') and s.lastName like CONCAT('%',:lastName,'%')";
   // String FILTER_STUDENTS_ON_FIRST_NAME_AND_LAST_NAME_QUERY = "select * from students s where s.first_name like CONCAT('%',:firstName,'%') and s.last_name like CONCAT('%',:lastName,'%')";
    @Query(FILTER_STUDENTS_ON_FIRST_NAME_AND_LAST_NAME_QUERY)
    // @Query(value = FILTER_STUDENTS_ON_FIRST_NAME_AND_LAST_NAME_QUERY, nativeQuery = true)
    Page<Student> findByFirstNameLikeAndLastNameLike(String firstName, String lastName, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update students set birth_date = :birthDate where id = :id", nativeQuery = true)
    int updateStudentBirthDate(LocalDate birthDate, Long id);

    @Query(name = "Student.findByEmailNamedQuery") // Optional
    List<Student> findByEmailNamedQuery(@Param("email") String email);
}
