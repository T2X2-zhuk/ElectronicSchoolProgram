package SchoolLessonsAndCertificates.validators.MethodsValidatorClasses;

import SchoolLessonsAndCertificates.jpa.database.CertificateRepository;
import SchoolLessonsAndCertificates.util.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidatorClassWithMethodsForCertificatesParameters {

    private final ValidationErrorFactory errorFactory;
    private final CertificateRepository repository;

    public Optional<ValidationError> validateCertificatesAlreadyExists(Long studentId,Long schoolClassId){
        boolean exists =
                repository.existsByStudentIdAndClassLesson_SchoolClassId(studentId,schoolClassId);
        return exists
                ? Optional.of(errorFactory.buildError(
                "LESSONS_AND_CERTIFICATES_ERROR_CODE_6"))
                : Optional.empty();
    }
}
