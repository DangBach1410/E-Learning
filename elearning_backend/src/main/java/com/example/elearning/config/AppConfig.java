package com.example.elearning.config;

import com.example.elearning.dto.CourseDTO;
import com.example.elearning.dto.LessonDTO;
import com.example.elearning.model.Course;
import com.example.elearning.model.Lesson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(CourseDTO.class, Course.class)
                .addMappings(m -> m.skip(Course::setId));
        mapper.typeMap(LessonDTO.class, Lesson.class)
                .addMappings(m -> m.skip(Lesson::setId));

        return mapper;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
