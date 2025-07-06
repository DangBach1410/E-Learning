package com.example.elearning.controller;

import com.example.elearning.dto.CourseDTO;
import com.example.elearning.service.CourseService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "CourseList";
    }

    @GetMapping("/{id}")
    public String getCourseById(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "CourseDetail";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new CourseDTO());
        return "CourseCreateForm";
    }

    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute("course") CourseDTO courseDTO) {
        courseService.createCourse(courseDTO);
        return "redirect:/courses";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "CourseUpdateForm";
    }

    @PostMapping("/{id}")
    public String updateCourse(@PathVariable Long id, @Valid @ModelAttribute("course") CourseDTO courseDTO) {
        courseService.updateCourse(id, courseDTO);
        return "redirect:/courses";
    }

    @PostMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @GetMapping("/search")
    public String searchCourses(@RequestParam String q, Model model) {
        model.addAttribute("courses", courseService.searchCourses(q));
        return "CourseList";
    }

//    @GetMapping("/category/{id}")
//    public String getCoursesByCategory(@PathVariable Long id, Model model) {
//        model.addAttribute("courses", courseService.getCoursesByCategory(id));
//        return "CourseList";
//    }
}