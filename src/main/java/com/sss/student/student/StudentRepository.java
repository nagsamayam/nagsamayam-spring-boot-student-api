package com.sss.student.student;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findOneByUuid(UUID uuid);
    Student findOneByEmail(String email);
    List<Student> findAllByFirstNameContaining(String firstName);
    List<Student> findAllByEmailContaining(String email);

    @Transactional
    void deleteByUuid(UUID uuid);

    String FILTER_STUDENTS_ON_FIRST_NAME_AND_LAST_NAME_QUERY = "select * from students s where s.first_name like CONCAT('%',?1,'%') and s.last_name like CONCAT('%',?2,'%')";
    @Query(value = FILTER_STUDENTS_ON_FIRST_NAME_AND_LAST_NAME_QUERY, nativeQuery = true)
    Page<Student> findByFirstNameLikeAndLastNameLike(String firstName, String lastName, Pageable pageable);
}
