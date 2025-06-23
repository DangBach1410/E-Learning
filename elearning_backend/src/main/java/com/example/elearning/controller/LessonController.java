package com.example.elearning.controller;

import com.example.elearning.dto.LessonDTO;
import com.example.elearning.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/courses/{courseId}/lessons")
    public ResponseEntity<?> addLessonToCourse(@PathVariable Long courseId, @RequestBody LessonDTO lessonDTO) {
        return ResponseEntity.ok(lessonService.addLesson(courseId, lessonDTO));
    }

    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<?> getLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonService.getLesson(lessonId));
    }

    @PutMapping("/lessons/{lessonId}")
    public ResponseEntity<?> updateLesson(@PathVariable Long lessonId, @RequestBody LessonDTO lessonDTO) {
        return ResponseEntity.ok(lessonService.updateLesson(lessonId, lessonDTO));
    }

    @DeleteMapping("/lessons/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.ok().build();
    }
}

