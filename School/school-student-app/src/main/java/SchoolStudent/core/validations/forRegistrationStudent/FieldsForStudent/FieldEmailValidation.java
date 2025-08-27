package SchoolStudent.core.validations.forRegistrationStudent.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.forRegistrationStudent.RequestFieldsValidationImpl;

import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 class FieldEmailValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidatorClassWithMethodsForParametersEmailAndPassword validation;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
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
