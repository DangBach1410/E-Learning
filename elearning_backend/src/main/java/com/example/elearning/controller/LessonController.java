package com.example.elearning.controller;

import com.example.elearning.dto.LessonDTO;
import com.example.elearning.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/{courseId}")
    public ResponseEntity<?> addLessonToCourse(@PathVariable Long courseId, @RequestBody @Valid LessonDTO lessonDTO) {
        return ResponseEntity.ok(lessonService.addLesson(courseId, lessonDTO));
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<?> getLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonService.getLesson(lessonId));
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<?> updateLesson(@PathVariable Long lessonId, @RequestBody @Valid LessonDTO lessonDTO) {
        return ResponseEntity.ok(lessonService.updateLesson(lessonId, lessonDTO));
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.ok().build();
    }
}

