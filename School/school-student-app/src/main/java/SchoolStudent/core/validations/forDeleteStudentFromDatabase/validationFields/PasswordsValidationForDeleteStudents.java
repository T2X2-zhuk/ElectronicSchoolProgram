package SchoolStudent.core.validations.forDeleteStudentFromDatabase.validationFields;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.validations.forDeleteStudentFromDatabase.RequestFieldsValidationForDeleteStudentImpl;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PasswordsValidationForDeleteStudents extends RequestFieldsValidationForDeleteStudentImpl {

    @Autowired private ValidatorClassWithMethodsForParametersEmailAndPassword validation;
    @Override
    public Optional<ValidationErrorDTO> validate(DeleteStudentFromDatabaseRequest request) {
        return validation.notEnteredASingleCorrectPassword(request.getPasswords());
    }
}
