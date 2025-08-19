package SchoolStudent.core.validations.forRegistrationStudent;

import SchoolStudent.core.database.CodeForRegistrationRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.ValidationErrorFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.ValidationMethodsFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 class SpecificCodeForRegistrationValidation extends RequestFieldsValidationImpl{

    @Autowired private ValidationMethodsFactory validationMethodsFactory;
    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation = validationMethodsFactory.getValidatorsMethodsList().stream()
                .filter(validator -> validator instanceof ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode)
                .map(validator -> (ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode) validator)
                .findFirst()
                .orElse(null);
        assert validation != null;
        if (validation.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistration()).isPresent()){
            return validation.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistration());
        }  if (validation.theWrongSpecificCodeForRegistration(request.getSpecificCodeForRegistration()).isPresent()){
            return validation.theWrongSpecificCodeForRegistration(request.getSpecificCodeForRegistration());
        }
        return Optional.empty();
    }
}
