package com.sss.student.school;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sss.student.student.Student;
import com.sss.student.utils.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schools")
public class School extends BaseEntity {
    @Column(length = 100)
    private String name;

    @OneToMany(
            mappedBy = "school"
    )
    @JsonManagedReference
    private List<Student> students;

    public School(String name){
        this.name = name;
    }

}
