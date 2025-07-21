package SchoolStudent.core.validations.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class FieldLastNameValidation extends RequestFieldsValidationImpl {

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        return (isNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_2"))
                : Optional.empty();
    }

    private boolean isNullOrBlank(RegistrationStudentInDatabaseRequest request) {
        return request.getLastName() == null || request.getLastName().isBlank();
    }
}
