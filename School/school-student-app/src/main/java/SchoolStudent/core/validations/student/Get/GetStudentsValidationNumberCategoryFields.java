package SchoolStudent.core.validations.student.Get;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class GetStudentsValidationNumberCategoryFields implements GetStudentsValidation{

    @Autowired
    private ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation;
    @Override
    public List<ValidationErrorDTO> validationErrorDTOSList(GetAllStudentsBySchoolClassRequest request) {
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validateNumber(request).ifPresent(errorDTOS::add);
        validateCategory(request).ifPresent(errorDTOS::add);
        return errorDTOS;
    }

    private Optional<ValidationErrorDTO> validateNumber(GetAllStudentsBySchoolClassRequest request) {
        if (validation.mustNotBeEmptyNumber(request.getNumber()).isPresent()){
            return validation.mustNotBeEmptyNumber(request.getNumber());
        }if (validation.notSuchSchoolClass(request.getNumber()).isPresent()){
            return validation.notSuchSchoolClass(request.getNumber());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateCategory(GetAllStudentsBySchoolClassRequest request) {
        if (validation.categoryClassMustNotBeEmpty(request.getCategory()).isPresent()){
            return validation.categoryClassMustNotBeEmpty(request.getCategory());
        } else if (validation.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory()).isPresent()){
            return validation.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory());
        } else if (validation.suchCategoryClassIsNotInDatabase(request.getCategory()).isPresent()){
            return validation.suchCategoryClassIsNotInDatabase(request.getCategory());
        }
        return Optional.empty();
    }
}
