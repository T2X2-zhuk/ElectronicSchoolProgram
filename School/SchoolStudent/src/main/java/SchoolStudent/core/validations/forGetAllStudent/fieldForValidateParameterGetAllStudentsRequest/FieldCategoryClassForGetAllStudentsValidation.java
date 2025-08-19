package SchoolStudent.core.validations.forGetAllStudent.fieldForValidateParameterGetAllStudentsRequest;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.validations.forGetAllStudent.GetAllStudentsBySchoolClassRequestFieldsBySchoolClassValidationImpl;
import SchoolStudent.core.validations.validatorMethodsFactory.ValidationMethodsFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FieldCategoryClassForGetAllStudentsValidation extends GetAllStudentsBySchoolClassRequestFieldsBySchoolClassValidationImpl {

    @Autowired private ValidationMethodsFactory validationMethodsFactory;
    @Override
    public Optional<ValidationErrorDTO> validate(GetAllStudentsBySchoolClassRequest request) {
        ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation = validationMethodsFactory.getValidatorsMethodsList().stream()
                .filter(validator -> validator instanceof ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode)
                .map(validator -> (ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode) validator)
                .findFirst()
                .orElse(null);
        assert validation != null;
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
