package SchoolStudent.core.validations.forRegistrationStudent.FieldsForStudent;

import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.forRegistrationStudent.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.ValidationErrorFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.ValidationMethodsFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 class FieldPasswordValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidationMethodsFactory validationMethodsFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        ValidatorClassWithMethodsForParametersEmailAndPassword validation = validationMethodsFactory.getValidatorsMethodsList().stream()
                .filter(validator -> validator instanceof ValidatorClassWithMethodsForParametersEmailAndPassword)
                .map(validator -> (ValidatorClassWithMethodsForParametersEmailAndPassword) validator)
                .findFirst()
                .orElse(null);
        assert validation != null;
        if (validation.mustNotBeEmptyPassword(request.getPassword()).isPresent()){
            return validation.mustNotBeEmptyPassword(request.getPassword());
        }else if (validation.suchPasswordAlreadyExistsValidation(request.getPassword()).isPresent()){
            return validation.suchPasswordAlreadyExistsValidation(request.getPassword());
        }
        return Optional.empty();
    }
}
