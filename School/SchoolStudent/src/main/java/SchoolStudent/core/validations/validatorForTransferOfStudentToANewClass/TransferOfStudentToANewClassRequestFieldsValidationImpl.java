package SchoolStudent.core.validations.validatorForTransferOfStudentToANewClass;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.validatorForRegistrationStudent.FieldForRegistrationStudentValidation;

import java.util.List;
import java.util.Optional;

public abstract class TransferOfStudentToANewClassRequestFieldsValidationImpl implements FieldForTransferOfStudentToANewClassValidation {

    @Override
    public Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(TransferOfStudentToANewClassRequest request) {
        return null;
    }

}
