package com.sss.student.student;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findOneByUuid(UUID uuid);
    Student findOneByEmail(String email);
    List<Student> findAllByFirstNameContaining(String firstName);
    List<Student> findAllByEmailContaining(String email);

    @Transactional
    void deleteByUuid(UUID uuid);
}
