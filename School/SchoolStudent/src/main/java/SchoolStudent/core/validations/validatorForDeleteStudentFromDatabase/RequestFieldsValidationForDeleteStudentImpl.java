package SchoolStudent.core.validations.validatorForDeleteStudentFromDatabase;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;


import java.util.List;
import java.util.Optional;

public abstract class RequestFieldsValidationForDeleteStudentImpl implements ForDeleteStudentValidation {

    @Override
    public Optional<ValidationErrorDTO> validate(List<String> passwords) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(List<String> passwords) {
        return null;
    }

}
