package SchoolStudent.core.validations.forRegistrationStudent.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.forRegistrationStudent.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 class FieldPasswordValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidatorClassWithMethodsForParametersEmailAndPassword validation;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        if (validation.mustNotBeEmptyPassword(request.getPassword()).isPresent()){
            return validation.mustNotBeEmptyPassword(request.getPassword());
        }else if (validation.suchPasswordAlreadyExistsValidation(request.getPassword()).isPresent()){
            return validation.suchPasswordAlreadyExistsValidation(request.getPassword());
        }
        return Optional.empty();
    }
}
