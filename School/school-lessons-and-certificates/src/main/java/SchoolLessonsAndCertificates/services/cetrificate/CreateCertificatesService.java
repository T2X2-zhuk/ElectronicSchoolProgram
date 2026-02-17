package SchoolLessonsAndCertificates.services.cetrificate;

import SchoolLessonsAndCertificates.jpa.database.CertificateRepository;
import SchoolLessonsAndCertificates.jpa.database.ClassLessonRepository;
import SchoolLessonsAndCertificates.jpa.domain.Certificate;
import SchoolLessonsAndCertificates.request.certificate.CreateCertificatesRequest;
import SchoolLessonsAndCertificates.response.CreateCertificatesResponse;
import SchoolLessonsAndCertificates.util.ValidationError;
import SchoolLessonsAndCertificates.validators.certificate.CreateCertificatesValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateCertificatesService {

    private final CertificateRepository certificateRepository;
    private final ClassLessonRepository classLessonRepository;
    private final CreateCertificatesValidator validator;

    @Transactional
    public CreateCertificatesResponse execute(CreateCertificatesRequest request) {
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return CreateCertificatesResponse.builder().errors(errors).build();
        }
        List<Certificate> certificates = getCertificates(request);
        certificateRepository.saveAll(certificates);
        return CreateCertificatesResponse.builder()
                .message("Certificates for this student successfully created.")
                .build();
    }

    private List<Certificate> getCertificates(CreateCertificatesRequest request) {
        return classLessonRepository
                .findBySchoolClassId(request.getSchoolClassId())
                .stream()
                .map(classLesson -> Certificate.builder()
                        .classLesson(classLesson)
                        .studentId(request.getStudentId())
                        .build())
                .toList();
    }
}
