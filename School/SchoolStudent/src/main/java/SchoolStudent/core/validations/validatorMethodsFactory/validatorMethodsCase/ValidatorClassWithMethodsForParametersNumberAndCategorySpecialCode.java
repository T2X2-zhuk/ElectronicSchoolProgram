package SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase;

import SchoolStudent.core.database.CodeForRegistrationRepository;
import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 public class ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode implements ValidatorFieldMethods {
    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired
    private SchoolClassRepository repository;

    @Autowired private CodeForRegistrationRepository code;

    public Optional<ValidationErrorDTO> categoryClassMustNotBeEmpty(String category){
        return (isNullOrBlankOrEmpty(category))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }
    public Optional<ValidationErrorDTO> fieldCategoryClassMustContainOneCapitalEnglishLetter(String category){
        String regex = "^[A-Z]$";
        return (!category.matches(regex))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> suchCategoryClassIsNotInDatabase(String category){
        return (repository.findBycategory(category).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> mustNotBeEmptyNumber(Long number){
        return (number == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }


    public Optional<ValidationErrorDTO> notSuchSchoolClass(Long number){
        return (repository.findAllNumberWhichEqualsThisParameter(number).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> mustNotBeEmptySpecialCode(String specificCodeForRegistration){
        return (isNullOrBlankOrEmpty(specificCodeForRegistration))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_15"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> theWrongSpecificCodeForRegistration(String specificCodeForRegistration){
        return (code.findByspecificCodeForRegistrationForStudent(specificCodeForRegistration).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_16"))
                : Optional.empty();
    }
    private boolean isNullOrBlankOrEmpty(String parameter) {
        return parameter == null || parameter.isBlank() || parameter.isEmpty();
    }
}
