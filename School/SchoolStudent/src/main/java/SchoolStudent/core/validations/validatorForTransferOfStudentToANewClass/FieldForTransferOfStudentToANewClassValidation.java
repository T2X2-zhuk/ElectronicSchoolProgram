package SchoolStudent.core.validations.validatorForTransferOfStudentToANewClass;



import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;

import java.util.List;
import java.util.Optional;

 interface FieldForTransferOfStudentToANewClassValidation {

    Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request);

    List<ValidationErrorDTO> validateList(TransferOfStudentToANewClassRequest request);

}
