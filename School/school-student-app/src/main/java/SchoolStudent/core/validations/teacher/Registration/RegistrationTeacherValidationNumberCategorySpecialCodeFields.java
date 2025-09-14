package SchoolStudent.core.validations.teacher.Registration;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
class RegistrationTeacherValidationNumberCategorySpecialCodeFields implements RegistrationTeacherValidation {
    @Autowired
    private ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation;

    @Override
    public List<ValidationErrorDTO> validationErrorDTOSList(RegistrationTeacherRequest request) {
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validateNumber(request).ifPresent(errorDTOS::add);
        validateCategory(request).ifPresent(errorDTOS::add);
        validateSpecificCode(request).ifPresent(errorDTOS::add);
        return errorDTOS;
    }

    private Optional<ValidationErrorDTO> validateNumber(RegistrationTeacherRequest request) {
        if (validation.mustNotBeEmptyNumber(request.getNumber()).isPresent()){
            return validation.mustNotBeEmptyNumber(request.getNumber());
        }if (validation.notSuchSchoolClass(request.getNumber()).isPresent()){
            return validation.notSuchSchoolClass(request.getNumber());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateCategory(RegistrationTeacherRequest request) {
        if (validation.categoryClassMustNotBeEmpty(request.getCategory()).isPresent()){
            return validation.categoryClassMustNotBeEmpty(request.getCategory());
        } else if (validation.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory()).isPresent()){
            return validation.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory());
        } else if (validation.suchCategoryClassIsNotInDatabase(request.getCategory()).isPresent()){
            return validation.suchCategoryClassIsNotInDatabase(request.getCategory());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateSpecificCode(RegistrationTeacherRequest request) {
        if (validation.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistrationTeacher()).isPresent()){
            return validation.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistrationTeacher());
        }  if (validation.theWrongSpecificCodeForRegistrationTeacher(request.getSpecificCodeForRegistrationTeacher()).isPresent()){
            return validation.theWrongSpecificCodeForRegistrationTeacher(request.getSpecificCodeForRegistrationTeacher());
        }
        return Optional.empty();
    }

}
