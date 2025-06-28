package com.example.elearning.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    @NotBlank(message = "Course name is required")
    @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Number of sessions is required")
    @Min(value = 1, message = "Number of sessions must be at least 1")
    private Integer numberOfSessions;

    @NotNull(message = "Duration hours is required")
    @Min(value = 1, message = "Duration hours must be at least 1")
    private Integer durationHours;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}
