package SchoolStudent.core.validations.student.Registration;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;

import java.util.List;

public interface RegistrationStudentValidation {

    List<ValidationErrorDTO> validationErrorDTOSList(RegistrationStudentInDatabaseRequest request);
}
