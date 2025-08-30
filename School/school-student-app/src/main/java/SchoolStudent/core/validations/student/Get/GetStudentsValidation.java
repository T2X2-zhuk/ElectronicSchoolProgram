package SchoolStudent.core.validations.student.Get;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;

import java.util.List;

public interface GetStudentsValidation {

    List<ValidationErrorDTO> validationErrorDTOSList(GetAllStudentsBySchoolClassRequest request);
}
