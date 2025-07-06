package com.example.elearning.controller;

import com.example.elearning.dto.LessonDTO;
import com.example.elearning.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/{lessonId}")
    public String getLesson(@PathVariable Long lessonId, Model model) {
        model.addAttribute("lesson", lessonService.getLesson(lessonId));
        return "LessonDetail";
    }

    @GetMapping("/new/{courseId}")
    public String showCreateForm(@PathVariable Long courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("lesson", new LessonDTO());
        return "LessonCreateForm";
    }

    @PostMapping("/{courseId}")
    public String addLessonToCourse(@PathVariable Long courseId, @Valid @ModelAttribute("lesson") LessonDTO lessonDTO) {
        lessonService.addLesson(courseId, lessonDTO);
        return "redirect:/courses/" + courseId;
    }

    @GetMapping("/{lessonId}/edit")
    public String showEditForm(@PathVariable Long lessonId, Model model) {
        model.addAttribute("lesson", lessonService.getLesson(lessonId));
        return "LessonUpdateForm";
    }

    @PostMapping("/{lessonId}/update")
    public String updateLesson(@PathVariable Long lessonId, @Valid @ModelAttribute("lesson") LessonDTO lessonDTO) {
        lessonService.updateLesson(lessonId, lessonDTO);
        return "redirect:/courses/" + lessonDTO.getCourseId();
    }

    @PostMapping("/{lessonId}/delete")
    public String deleteLesson(@PathVariable Long lessonId) {
        Long courseId = lessonService.getLesson(lessonId).getCourseId();
        lessonService.deleteLesson(lessonId);
        return "redirect:/courses/" + courseId;
    }
}