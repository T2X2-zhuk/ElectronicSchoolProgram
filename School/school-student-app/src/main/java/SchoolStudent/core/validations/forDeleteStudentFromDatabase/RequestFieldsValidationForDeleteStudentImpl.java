package SchoolStudent.core.validations.forDeleteStudentFromDatabase;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;


import java.util.List;
import java.util.Optional;

public abstract class RequestFieldsValidationForDeleteStudentImpl implements ForDeleteStudentValidation {

    @Override
    public Optional<ValidationErrorDTO> validate(DeleteStudentFromDatabaseRequest request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(DeleteStudentFromDatabaseRequest request) {
        return null;
    }

}
