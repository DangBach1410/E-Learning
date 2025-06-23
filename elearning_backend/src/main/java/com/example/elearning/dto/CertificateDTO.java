package com.example.elearning.dto;

import com.example.elearning.model.CertificateType;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDTO {

    private CertificateType type; // ASSOCIATE, PROFESSIONAL, MASTER

    private LocalDate issueDate;
}

