package com.example.elearning.service;

import com.example.elearning.dto.CourseDTO;
import com.example.elearning.model.Category;
import com.example.elearning.model.Course;
import com.example.elearning.repository.CategoryRepository;
import com.example.elearning.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {

    private final CourseRepository courseRepo;
    private final CategoryRepository categoryRepo;

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found"));
        return mapToDTO(course);
    }

    @Override
    public CourseDTO createCourse(CourseDTO dto) {
        if (courseRepo.existsByName(dto.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course name already exists");
        }

        Course course = new Course();
        course.setName(dto.getName());
        course.setNumberOfSessions(dto.getNumberOfSessions());
        course.setDurationHours(dto.getDurationHours());
        course.setDescription(dto.getDescription());
        course.setCategory(getCategory(dto.getCategoryId()));

        return mapToDTO(courseRepo.save(course));
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO dto) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found"));

        course.setName(dto.getName());
        course.setNumberOfSessions(dto.getNumberOfSessions());
        course.setDurationHours(dto.getDurationHours());
        course.setDescription(dto.getDescription());
        course.setCategory(getCategory(dto.getCategoryId()));

        return mapToDTO(courseRepo.save(course));
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found");
        }
        courseRepo.deleteById(id);
    }

    @Override
    public List<CourseDTO> searchCourses(String keyword) {
        return courseRepo.findByNameContainingIgnoreCase(keyword)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getCoursesByCategory(Long categoryId) {
        return courseRepo.findByCategoryId(categoryId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private CourseDTO mapToDTO(Course course) {
        return new CourseDTO(
                course.getName(),
                course.getNumberOfSessions(),
                course.getDurationHours(),
                course.getDescription(),
                course.getCategory().getId()
        );
    }

    private Category getCategory(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found"));
    }
}

