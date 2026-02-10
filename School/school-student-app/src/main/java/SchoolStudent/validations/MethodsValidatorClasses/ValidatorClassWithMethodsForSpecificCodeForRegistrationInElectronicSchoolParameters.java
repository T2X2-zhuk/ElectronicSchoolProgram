package SchoolStudent.validations.MethodsValidatorClasses;

import SchoolStudent.jpa.repositories.CodeForRegistrationRepository;
import SchoolStudent.util.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters {

    private final ValidationErrorFactory errorFactory;
    private final CodeForRegistrationRepository code;


    public Optional<ValidationError> mustNotBeEmptySpecialCode(String specificCodeForRegistration) {
        return (isNullOrBlankOrEmpty(specificCodeForRegistration))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_15"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateSpecificCodeForStudent(String specificCodeForRegistration) {
        return (code.findBySpecificCodeForRegistrationForStudent(specificCodeForRegistration).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_16"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateSpecificCodeForTeacher(String specificCodeForRegistration) {
        return (code.findBySpecificCodeForRegistrationForTeacher(specificCodeForRegistration).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_19"))
                : Optional.empty();
    }

    private boolean isNullOrBlankOrEmpty(String parameter) {
        return parameter == null || parameter.isBlank();
    }
}
