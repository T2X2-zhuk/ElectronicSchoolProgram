package SchoolStudent.validations.MethodsValidatorClasses;

import SchoolStudent.jpa.repositories.TeacherRepository;
import SchoolStudent.util.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidatorClassWithMethodsForTeacherParameters {

    private final ValidationErrorFactory errorFactory;
    private final TeacherRepository teacherRepository;

    public Optional<ValidationError> firstNameMustNotBeEmpty(String firstName) {
        return (isNullOrBlankOrEmpty(firstName))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }

    public Optional<ValidationError> mustNotBeEmptyEmail(String email) {
        return (isNullOrBlankOrEmpty(email))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }

    public Optional<ValidationError> lastNameMustNotBeEmpty(String lastName) {
        return (isNullOrBlankOrEmpty(lastName))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_2"))
                : Optional.empty();
    }

    public Optional<ValidationError> fatherlandMustNotBeEmpty(String fatherland) {
        return (isNullOrBlankOrEmpty(fatherland))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }

    public Optional<ValidationError> mustNotBeEmptyPassword(String password) {
        return (isNullOrBlankOrEmpty(password))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

    public Optional<ValidationError> subjectMustNotBeEmpty(String subject) {
        return (isNullOrBlankOrEmpty(subject))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_20"))
                : Optional.empty();
    }

    public Optional<ValidationError> suchEmailAlreadyExists(String email) {
        return (teacherRepository.findByEmail(email).isPresent())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_6"))
                : Optional.empty();
    }

    public Optional<ValidationError> emailYourEmailIsNotCorrectError(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        java.util.regex.Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return (!matcher.matches())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

    public Optional<ValidationError> suchPasswordAlreadyExists(String password) {
        return (teacherRepository.findByPassword(password).isPresent())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_9"))
                : Optional.empty();
    }

    private boolean isNullOrBlankOrEmpty(String parameter) {
        return parameter == null || parameter.isBlank();
    }
}
