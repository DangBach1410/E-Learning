package com.example.elearning.service;

import com.example.elearning.dto.LessonDTO;

import java.util.List;

public interface ILessonService {
    LessonDTO addLesson(Long courseId, LessonDTO dto);
    LessonDTO getLesson(Long id);
    LessonDTO updateLesson(Long id, LessonDTO dto);
    void deleteLesson(Long id);
}
