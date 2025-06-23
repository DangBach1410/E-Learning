package com.example.elearning.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {

    private String title;

    private Integer durationHours;

    private String description;
}
