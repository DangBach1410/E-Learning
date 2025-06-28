package com.example.elearning.repository;

import com.example.elearning.model.Certificate;
import com.example.elearning.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    Optional<Certificate> findByCoursesContaining(Course course);
}