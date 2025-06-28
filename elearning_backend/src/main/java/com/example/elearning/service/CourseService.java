package com.example.elearning.service;

import com.example.elearning.dto.CourseDTO;
import com.example.elearning.model.Category;
import com.example.elearning.model.Course;
import com.example.elearning.repository.CategoryRepository;
import com.example.elearning.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

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

        Course course = modelMapper.map(dto, Course.class);
        course.setCategory(getCategory(dto.getCategoryId()));

        return mapToDTO(courseRepo.save(course));
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO dto) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found"));

        modelMapper.map(dto, course);
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
        CourseDTO dto = modelMapper.map(course, CourseDTO.class);
        dto.setCategoryId(course.getCategory().getId());
        return dto;
    }

    private Category getCategory(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found"));
    }
}