package SchoolLessonsAndCertificates.validators.MethodsValidatorClasses;

import SchoolLessonsAndCertificates.jpa.database.LessonRepository;
import SchoolLessonsAndCertificates.util.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidatorClassWithMethodsForLessonParameters {

    private final ValidationErrorFactory errorFactory;
    private final LessonRepository lessonRepository;

    public Optional<ValidationError> lessonNameMustNotBeEmpty(String name){
        return (isNullOrBlankOrEmpty(name))
                ? Optional.of(errorFactory.buildError("LESSONS_AND_CERTIFICATES_ERROR_CODE_2"))
                : Optional.empty();
    }

    public Optional<ValidationError> isLessonInDatabaseError(String subjectName) {
        return (!lessonRepository.existsByName(subjectName))
                ? Optional.of(errorFactory.buildError("LESSONS_AND_CERTIFICATES_ERROR_CODE_1"))
                : Optional.empty();
    }

    private boolean isNullOrBlankOrEmpty(String parameter) {
        return parameter == null || parameter.isBlank();
    }
}
