package SchoolStudent.core.validations.forRegistrationStudent;



import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;

import java.util.List;
import java.util.Optional;

 public interface FieldForRegistrationStudentValidation {

    Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request);

    List<ValidationErrorDTO> validateList(RegistrationStudentInDatabaseRequest request);

}
