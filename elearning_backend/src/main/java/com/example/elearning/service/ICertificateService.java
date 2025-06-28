package com.example.elearning.service;

import com.example.elearning.dto.CertificateDTO;

import java.util.List;

public interface ICertificateService {
    List<CertificateDTO> getAllCertificates();
    CertificateDTO getCertificateByCourse(Long courseId);
    CertificateDTO createCertificate(CertificateDTO dto);
    CertificateDTO updateCertificate(Long id, CertificateDTO dto);
    void deleteCertificate(Long id);
}

