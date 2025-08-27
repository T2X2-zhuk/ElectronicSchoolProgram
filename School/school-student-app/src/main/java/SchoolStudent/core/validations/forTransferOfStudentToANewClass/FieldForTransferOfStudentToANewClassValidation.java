package SchoolStudent.core.validations.forTransferOfStudentToANewClass;



import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;

import java.util.List;
import java.util.Optional;

 interface FieldForTransferOfStudentToANewClassValidation {

    Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request);

    List<ValidationErrorDTO> validateList(TransferOfStudentToANewClassRequest request);

}
