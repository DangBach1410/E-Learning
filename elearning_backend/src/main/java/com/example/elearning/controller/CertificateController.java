package com.example.elearning.controller;

import com.example.elearning.dto.CertificateDTO;
import com.example.elearning.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public ResponseEntity<?> getAllCertificates() {
        return ResponseEntity.ok(certificateService.getAllCertificates());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCertificateByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(certificateService.getCertificateByCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<?> createCertificate(@RequestBody CertificateDTO certificateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(certificateService.createCertificate(certificateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCertificate(@PathVariable Long id, @RequestBody CertificateDTO certificateDTO) {
        return ResponseEntity.ok(certificateService.updateCertificate(id, certificateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCertificate(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
        return ResponseEntity.noContent().build();
    }

}


