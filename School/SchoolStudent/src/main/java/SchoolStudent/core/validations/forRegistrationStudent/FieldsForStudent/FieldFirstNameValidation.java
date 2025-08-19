package SchoolStudent.core.validations.forRegistrationStudent.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.forRegistrationStudent.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.validatorMethodsFactory.ValidationMethodsFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
 class FieldFirstNameValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidationMethodsFactory validationMethodsFactory;

    @Override
        public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland validation = validationMethodsFactory.getValidatorsMethodsList().stream()
                .filter(validator -> validator instanceof ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland)
                .map(validator -> (ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland) validator)
                .findFirst()
                .orElse(null);
        assert validation != null;
        if (validation.firstNameMustNotBeEmpty(request.getFirstName()).isPresent()){
            return validation.firstNameMustNotBeEmpty(request.getFirstName());
        }
        return Optional.empty();
    }
}
