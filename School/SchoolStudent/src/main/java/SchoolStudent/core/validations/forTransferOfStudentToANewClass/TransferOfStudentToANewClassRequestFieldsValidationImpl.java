package SchoolStudent.core.validations.forTransferOfStudentToANewClass;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;

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
