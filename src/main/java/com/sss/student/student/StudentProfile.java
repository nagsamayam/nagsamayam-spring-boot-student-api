package com.sss.student.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @UuidGenerator
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(length = 150)
    private String bio;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    public StudentProfile(){}

    public StudentProfile(String bio){
        this.bio = bio;
    }
}
