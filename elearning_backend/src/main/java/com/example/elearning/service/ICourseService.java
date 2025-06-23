package com.example.elearning.service;

import com.example.elearning.dto.CourseDTO;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO getCourseById(Long id);
    CourseDTO createCourse(CourseDTO dto);
    CourseDTO updateCourse(Long id, CourseDTO dto);
    void deleteCourse(Long id);
    List<CourseDTO> searchCourses(String keyword);
    List<CourseDTO> getCoursesByCategory(Long categoryId);
}

