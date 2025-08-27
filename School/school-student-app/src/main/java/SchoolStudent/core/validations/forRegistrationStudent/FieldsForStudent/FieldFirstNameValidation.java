package SchoolStudent.core.validations.forRegistrationStudent.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.forRegistrationStudent.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
 class FieldFirstNameValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland validation;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        if (validation.firstNameMustNotBeEmpty(request.getFirstName()).isPresent()){
            return validation.firstNameMustNotBeEmpty(request.getFirstName());
        }
        return Optional.empty();
    }
}
