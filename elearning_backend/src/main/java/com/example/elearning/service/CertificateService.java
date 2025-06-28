package com.example.elearning.service;

import com.example.elearning.dto.CertificateDTO;
import com.example.elearning.model.Certificate;
import com.example.elearning.model.Course;
import com.example.elearning.repository.CertificateRepository;
import com.example.elearning.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateService implements ICertificateService {

    private final CertificateRepository certificateRepo;
    private final CourseRepository courseRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<CertificateDTO> getAllCertificates() {
        return certificateRepo.findAll()
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CertificateDTO getCertificateByCourse(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        Certificate cert = certificateRepo.findByCoursesContaining(course)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate not found"));
        return mapToDTO(cert);
    }

    @Override
    public CertificateDTO createCertificate(CertificateDTO dto) {
        Certificate certificate = modelMapper.map(dto, Certificate.class);
        return mapToDTO(certificateRepo.save(certificate));
    }

    private CertificateDTO mapToDTO(Certificate c) {
        return modelMapper.map(c, CertificateDTO.class);
    }

    @Override
    public CertificateDTO updateCertificate(Long id, CertificateDTO dto) {
        Certificate certificate = certificateRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificate not found"));
        modelMapper.map(dto, certificate);
        return mapToDTO(certificateRepo.save(certificate));
    }

    @Override
    public void deleteCertificate(Long id) {
        Certificate certificate = certificateRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificate not found"));
        certificateRepo.delete(certificate);
    }
}

