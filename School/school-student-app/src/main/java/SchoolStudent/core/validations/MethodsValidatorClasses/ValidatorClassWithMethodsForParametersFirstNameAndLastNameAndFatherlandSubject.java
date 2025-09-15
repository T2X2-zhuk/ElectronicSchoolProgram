package SchoolStudent.core.validations.MethodsValidatorClasses;

import SchoolStudent.core.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherlandSubject {

    @Autowired
    private ValidationErrorFactory errorFactory;

    public Optional<ValidationErrorDTO> firstNameMustNotBeEmpty(String firstName) {
        return (isNullOrBlankOrEmpty(firstName))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> lastNameMustNotBeEmpty(String lastName) {
        return (isNullOrBlankOrEmpty(lastName))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_2"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> fatherlandMustNotBeEmpty(String fatherland){
        return (isNullOrBlankOrEmpty(fatherland))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> subjectMustNotBeEmpty(String subject){
        return (isNullOrBlankOrEmpty(subject))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_20"))
                : Optional.empty();
    }
    private boolean isNullOrBlankOrEmpty(String parameter) {
        return parameter == null || parameter.isBlank() || parameter.isEmpty();
    }
}
