package SchoolStudent.core.validations.student.Transfer;



import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.TransferOfStudentToANewClassRequest;

import java.util.List;

public interface TransferStudentValidation {

    List<ValidationErrorDTO> validateList(TransferOfStudentToANewClassRequest request);

}
