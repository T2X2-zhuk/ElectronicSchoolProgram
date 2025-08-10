package SchoolStudent.core.validations.validatorForDeleteStudentFromDatabase;



import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;

import java.util.List;
import java.util.Optional;

public interface ForDeleteStudentValidation {

    Optional<ValidationErrorDTO> validate(List<String> passwords);

    List<ValidationErrorDTO> validateList(List<String> passwords);

}
