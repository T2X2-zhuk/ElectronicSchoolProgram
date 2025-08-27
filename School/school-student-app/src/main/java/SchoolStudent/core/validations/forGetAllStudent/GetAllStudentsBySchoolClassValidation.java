package SchoolStudent.core.validations.forGetAllStudent;



import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;

import java.util.List;
import java.util.Optional;

 public interface GetAllStudentsBySchoolClassValidation {

    Optional<ValidationErrorDTO> validate(GetAllStudentsBySchoolClassRequest request);

    List<ValidationErrorDTO> validateList(GetAllStudentsBySchoolClassRequest request);

}
