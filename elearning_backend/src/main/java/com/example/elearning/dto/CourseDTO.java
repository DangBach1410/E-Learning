package com.example.elearning.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private String name;

    private Integer numberOfSessions;

    private Integer durationHours;

    private String description;

    private Long categoryId;
}
