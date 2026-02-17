package SchoolLessonsAndCertificates.validators.certificate;

import SchoolLessonsAndCertificates.request.CreateCertificatesRequest;
import SchoolLessonsAndCertificates.util.ValidationError;
import SchoolLessonsAndCertificates.validators.MethodsValidatorClasses.ValidatorClassWithMethodsForCertificatesParameters;
import SchoolLessonsAndCertificates.validators.MethodsValidatorClasses.ValidatorClassWithMethodsForClassLessonParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateCertificatesValidator {

    private final ValidatorClassWithMethodsForClassLessonParameters validatorClassWithMethodsForClassLessonParameters;
    private final ValidatorClassWithMethodsForCertificatesParameters validatorClassWithMethodsForCertificates;

    public List<ValidationError> validate(CreateCertificatesRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatorClassWithMethodsForClassLessonParameters.
                validateFindClassLessonsBySchoolClassId(
                        request.getSchoolClassId()).ifPresent(errors::add);
        if (!errors.isEmpty()) {
            return errors;
        }
        validatorClassWithMethodsForCertificates.validateCertificatesAlreadyExists(request.getStudentId(), request.getSchoolClassId()).ifPresent(errors::add);
        return errors;
    }
}
