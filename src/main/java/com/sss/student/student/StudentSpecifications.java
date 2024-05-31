package com.sss.student.student;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecifications {
    public static Specification<Student> hasEmail(String email) {
        return (
                Root<Student> root,
                CriteriaQuery<?> query,
                CriteriaBuilder builder
                ) -> {
            if(email.isEmpty()) {return null;};

            return builder.equal(root.get("email"), email);
        };
    }

    public static Specification<Student> firstNameLike(String firstName) {
        return (
                Root<Student> root,
                CriteriaQuery<?> query,
                CriteriaBuilder builder
        ) -> {
            if (firstName.isEmpty()) {
                return null;
            }

            return builder.like(root.get("firstName"), firstName);
        };
    }
}
