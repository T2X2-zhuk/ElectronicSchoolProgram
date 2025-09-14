package SchoolStudent.core.validations.student.Registration;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
class RegistrationStudentValidationNumberCategoryFields implements RegistrationStudentValidation {
    @Autowired
    private ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation;

    @Override
    public List<ValidationErrorDTO> validationErrorDTOSList(RegistrationStudentInDatabaseRequest request) {
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validateNumber(request).ifPresent(errorDTOS::add);
        validateCategory(request).ifPresent(errorDTOS::add);
        validateSpecificCode(request).ifPresent(errorDTOS::add);
        return errorDTOS;
    }

    private Optional<ValidationErrorDTO> validateNumber(RegistrationStudentInDatabaseRequest request) {
        if (validation.mustNotBeEmptyNumber(request.getNumber()).isPresent()){
            return validation.mustNotBeEmptyNumber(request.getNumber());
        }if (validation.notSuchSchoolClass(request.getNumber()).isPresent()){
            return validation.notSuchSchoolClass(request.getNumber());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateCategory(RegistrationStudentInDatabaseRequest request) {
        if (validation.categoryClassMustNotBeEmpty(request.getCategory()).isPresent()){
            return validation.categoryClassMustNotBeEmpty(request.getCategory());
        } else if (validation.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory()).isPresent()){
            return validation.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory());
        } else if (validation.suchCategoryClassIsNotInDatabase(request.getCategory()).isPresent()){
            return validation.suchCategoryClassIsNotInDatabase(request.getCategory());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateSpecificCode(RegistrationStudentInDatabaseRequest request) {
        if (validation.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistration()).isPresent()){
            return validation.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistration());
        }  if (validation.theWrongSpecificCodeForRegistrationStudent(request.getSpecificCodeForRegistration()).isPresent()){
            return validation.theWrongSpecificCodeForRegistrationStudent(request.getSpecificCodeForRegistration());
        }
        return Optional.empty();
    }

}
