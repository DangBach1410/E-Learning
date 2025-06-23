package com.example.elearning.service;

import com.example.elearning.dto.CertificateDTO;
import com.example.elearning.model.Certificate;
import com.example.elearning.model.Course;
import com.example.elearning.repository.CertificateRepository;
import com.example.elearning.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<CertificateDTO> getAllCertificates() {
        return certificateRepo.findAll()
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CertificateDTO getCertificateByCourse(Long courseId) {
        Certificate cert = certificateRepo.findByCourseId(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate not found"));
        return mapToDTO(cert);
    }

    @Override
    public CertificateDTO createCertificate(Long courseId, CertificateDTO dto) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found"));

        if (certificateRepo.existsByCourseId(courseId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate already exists for this course");
        }

        Certificate cert = new Certificate(null, dto.getType(), course, dto.getIssueDate());
        return mapToDTO(certificateRepo.save(cert));
    }

    private CertificateDTO mapToDTO(Certificate c) {
        return new CertificateDTO(c.getType(), c.getIssueDate());
    }
}

