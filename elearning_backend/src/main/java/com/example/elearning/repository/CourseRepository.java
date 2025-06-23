package com.example.elearning.repository;

import com.example.elearning.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByName(String name);

    List<Course> findByNameContainingIgnoreCase(String keyword);

    List<Course> findByCategoryId(Long categoryId);
}
