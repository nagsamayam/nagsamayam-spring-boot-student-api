package com.sss.student.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sss.student.school.School;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @UuidGenerator
    private UUID uuid;

    @Column(length = 50)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Column(unique = true)
    private String email;

    private LocalDate birthDate;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    // ManyToOne always should have JoinColumn
    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private School school;

    public Student(){}

    public Student(String firstName, String lastName, String email, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

}
