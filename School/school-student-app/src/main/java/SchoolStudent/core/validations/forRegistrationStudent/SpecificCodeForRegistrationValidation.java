package SchoolStudent.core.validations.forRegistrationStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 class SpecificCodeForRegistrationValidation extends RequestFieldsValidationImpl{

    @Autowired private ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation;
    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        if (validation.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistration()).isPresent()){
            return validation.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistration());
        }  if (validation.theWrongSpecificCodeForRegistration(request.getSpecificCodeForRegistration()).isPresent()){
            return validation.theWrongSpecificCodeForRegistration(request.getSpecificCodeForRegistration());
        }
        return Optional.empty();
    }
}
