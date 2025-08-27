package SchoolStudent.core.validations.forRegistrationStudent.FieldsForStudent;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.forRegistrationStudent.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FieldNumberClassValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        if (validation.mustNotBeEmptyNumber(request.getNumber()).isPresent()){
            return validation.mustNotBeEmptyNumber(request.getNumber());
        }if (validation.notSuchSchoolClass(request.getNumber()).isPresent()){
            return validation.notSuchSchoolClass(request.getNumber());
        }
        return Optional.empty();
    }
}
