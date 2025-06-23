package com.example.elearning.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "certificates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CertificateType type; // Enum: ASSOCIATE, PROFESSIONAL, MASTER

    @OneToOne
    @JoinColumn(name = "course_id", unique = true)
    private Course course;

    @Column(nullable = false)
    private LocalDate issueDate;
}

