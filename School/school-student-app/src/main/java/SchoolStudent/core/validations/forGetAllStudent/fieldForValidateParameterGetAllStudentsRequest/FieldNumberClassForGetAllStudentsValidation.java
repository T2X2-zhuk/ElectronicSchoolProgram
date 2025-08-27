package SchoolStudent.core.validations.forGetAllStudent.fieldForValidateParameterGetAllStudentsRequest;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.validations.forGetAllStudent.GetAllStudentsBySchoolClassRequestFieldsBySchoolClassValidationImpl;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FieldNumberClassForGetAllStudentsValidation extends GetAllStudentsBySchoolClassRequestFieldsBySchoolClassValidationImpl {

    @Autowired private ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation;

    @Override
    public Optional<ValidationErrorDTO> validate(GetAllStudentsBySchoolClassRequest request) {
        if (validation.mustNotBeEmptyNumber(request.getNumber()).isPresent()){
            return validation.mustNotBeEmptyNumber(request.getNumber());
        }if (validation.notSuchSchoolClass(request.getNumber()).isPresent()){
            return validation.notSuchSchoolClass(request.getNumber());
        }
        return Optional.empty();
    }
}
