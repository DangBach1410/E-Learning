package com.example.elearning.dto;

import com.example.elearning.model.CertificateType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDTO {

    @NotNull(message = "Certificate type is required")
    @Enumerated(EnumType.STRING)
    private CertificateType type;
}

