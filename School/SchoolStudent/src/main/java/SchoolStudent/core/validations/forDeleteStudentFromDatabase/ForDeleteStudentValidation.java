package SchoolStudent.core.validations.forDeleteStudentFromDatabase;



import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;

import java.util.List;
import java.util.Optional;

public interface ForDeleteStudentValidation {

    Optional<ValidationErrorDTO> validate(DeleteStudentFromDatabaseRequest request);

    List<ValidationErrorDTO> validateList(DeleteStudentFromDatabaseRequest request);

}
