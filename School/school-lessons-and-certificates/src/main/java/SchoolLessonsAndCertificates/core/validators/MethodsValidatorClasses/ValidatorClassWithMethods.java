package SchoolLessonsAndCertificates.core.validators.MethodsValidatorClasses;

import SchoolLessonsAndCertificates.core.database.CertificateRepository;
import SchoolLessonsAndCertificates.core.database.LessonRepository;
import SchoolLessonsAndCertificates.core.database.StudentRepository;
import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.validators.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 public class ValidatorClassWithMethods {

    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private LessonRepository lessonRepository;

    public Optional<ValidationErrorDTO> isStudentInDatabaseError(String firstName, String lastName, String fatherland){
        return (studentRepository.findByFirstNameLastNameFatherland(firstName,lastName,fatherland).isPresent())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }

    private boolean isNullOrBlankOrEmpty(String parameter) {
        return parameter == null || parameter.isBlank() || parameter.isEmpty();
    }

}
