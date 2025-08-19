package SchoolStudent.core.validations.forRegistrationStudent;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;

import java.util.List;
import java.util.Optional;

public abstract class RequestFieldsValidationImpl implements FieldForRegistrationStudentValidation {

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(RegistrationStudentInDatabaseRequest request) {
        return null;
    }

}
