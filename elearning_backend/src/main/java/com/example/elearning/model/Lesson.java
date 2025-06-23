package com.example.elearning.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Integer durationHours;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}

