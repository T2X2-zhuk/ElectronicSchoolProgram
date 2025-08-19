package SchoolStudent.core.validations.forDeleteStudentFromDatabase.validationFields;

import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.validations.ValidationErrorFactory;
import SchoolStudent.core.validations.forDeleteStudentFromDatabase.RequestFieldsValidationForDeleteStudentImpl;
import SchoolStudent.core.validations.validatorMethodsFactory.ValidationMethodsFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PasswordsValidationForDeleteStudents extends RequestFieldsValidationForDeleteStudentImpl {

    @Autowired private ValidationMethodsFactory validationMethodsFactory;
    @Override
    public Optional<ValidationErrorDTO> validate(DeleteStudentFromDatabaseRequest request) {
        ValidatorClassWithMethodsForParametersEmailAndPassword validation = validationMethodsFactory.getValidatorsMethodsList().stream()
                .filter(validator -> validator instanceof ValidatorClassWithMethodsForParametersEmailAndPassword)
                .map(validator -> (ValidatorClassWithMethodsForParametersEmailAndPassword) validator)
                .findFirst()
                .orElse(null);
        assert validation != null;
        return validation.notEnteredASingleCorrectPassword(request.getPasswords());
    }
}
