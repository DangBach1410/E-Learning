package com.example.elearning.service;

import com.example.elearning.dto.LessonDTO;
import com.example.elearning.model.Course;
import com.example.elearning.model.Lesson;
import com.example.elearning.repository.CourseRepository;
import com.example.elearning.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService {

    private final LessonRepository lessonRepo;
    private final CourseRepository courseRepo;
    private final ModelMapper modelMapper;

    @Override
    public LessonDTO addLesson(Long courseId, LessonDTO dto) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found"));

        boolean isDuplicate = course.getLessons().stream()
                .anyMatch(lesson -> lesson.getTitle().equalsIgnoreCase(dto.getTitle()));

        if (isDuplicate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson title already exists in this course");
        }

        Lesson lesson = modelMapper.map(dto, Lesson.class);
        lesson.setCourse(course);
        return mapToDTO(lessonRepo.save(lesson));
    }

    @Override
    public LessonDTO getLesson(Long id) {
        return lessonRepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson not found"));
    }

    @Override
    public LessonDTO updateLesson(Long id, LessonDTO dto) {
        Lesson lesson = lessonRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson not found"));

        modelMapper.map(dto, lesson);
        return mapToDTO(lessonRepo.save(lesson));
    }

    @Override
    public void deleteLesson(Long id) {
        if (!lessonRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson not found");
        }
        lessonRepo.deleteById(id);
    }

    private LessonDTO mapToDTO(Lesson l) {
        return modelMapper.map(l, LessonDTO.class);
    }
}