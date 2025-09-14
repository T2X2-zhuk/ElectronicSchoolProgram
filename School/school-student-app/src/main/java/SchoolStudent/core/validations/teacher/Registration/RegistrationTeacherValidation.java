package SchoolStudent.core.validations.teacher.Registration;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;

import java.util.List;

public interface RegistrationTeacherValidation {

    List<ValidationErrorDTO> validationErrorDTOSList(RegistrationTeacherRequest request);
}
