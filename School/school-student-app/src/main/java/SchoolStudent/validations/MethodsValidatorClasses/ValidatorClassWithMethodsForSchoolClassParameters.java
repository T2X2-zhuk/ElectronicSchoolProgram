package SchoolStudent.validations.MethodsValidatorClasses;

import SchoolStudent.jpa.repositories.SchoolClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidatorClassWithMethodsForSchoolClassParameters {

    private final ValidationErrorFactory errorFactory;
    private final SchoolClassRepository schoolClassRepository;

    public Optional<ValidationError> numberMustNotBeEmpty(Long number) {
        return (number == null)
                ? Optional.of(errorFactory.buildError("SCHOOL_STUDENT_ERROR_CODE_10"))
                : Optional.empty();
    }

    public Optional<ValidationError> categoryMustNotBeEmpty(String category) {
        return (isNullOrBlankOrEmpty(category))
                ? Optional.of(errorFactory.buildError("SCHOOL_STUDENT_ERROR_CODE_12"))
                : Optional.empty();
    }

    public Optional<ValidationError> fieldCategoryClassMustContainOneCapitalEnglishLetter(String category) {
        String regex = "^[A-Z]$";
        return (!category.matches(regex))
                ? Optional.of(errorFactory.buildError("SCHOOL_STUDENT_ERROR_CODE_13"))
                : Optional.empty();
    }

    public Optional<ValidationError> suchSchoolClassIsNotExist(Long number, String category){
        return (schoolClassRepository.findByNumberAndCategory(number,category).isEmpty())
                ? Optional.of(errorFactory.buildError("SCHOOL_STUDENT_ERROR_CODE_22"))
                : Optional.empty();
    }

    public Optional<ValidationError> suchSchoolClassAlreadyExist(Long number, String category){
        return (schoolClassRepository.findByNumberAndCategory(number,category).isPresent())
        ? Optional.of(errorFactory.buildError("SCHOOL_STUDENT_ERROR_CODE_21"))
        : Optional.empty();
    }

    private boolean isNullOrBlankOrEmpty(String parameter) {
        return parameter == null || parameter.isBlank();
    }
}
