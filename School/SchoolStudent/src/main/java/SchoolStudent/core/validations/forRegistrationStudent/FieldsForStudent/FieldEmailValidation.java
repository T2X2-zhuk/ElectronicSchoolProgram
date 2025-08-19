package SchoolStudent.core.validations.forRegistrationStudent.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.forRegistrationStudent.RequestFieldsValidationImpl;

import SchoolStudent.core.validations.validatorMethodsFactory.ValidationMethodsFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorClassWithMethodsForParametersEmailAndPassword;
import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
@Component
 class FieldEmailValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidationMethodsFactory validationMethodsFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        ValidatorClassWithMethodsForParametersEmailAndPassword validation = validationMethodsFactory.getValidatorsMethodsList().stream()
                .filter(validator -> validator instanceof ValidatorClassWithMethodsForParametersEmailAndPassword)
                .map(validator -> (ValidatorClassWithMethodsForParametersEmailAndPassword) validator)
                .findFirst()
                .orElse(null);
        assert validation != null;
        if (validation.mustNotBeEmptyEmail(request.getEmail()).isPresent()){
            return validation.mustNotBeEmptyEmail(request.getEmail());
        }else if (validation.emailYourEmailIsNotCorrectError(request.getEmail()).isPresent()){
            return validation.emailYourEmailIsNotCorrectError(request.getEmail());
        }else if (validation.suchEmailAlreadyExistsError(request.getEmail()).isPresent()){
            return validation.suchEmailAlreadyExistsError(request.getEmail());
        }
        return Optional.empty();
    }
}
