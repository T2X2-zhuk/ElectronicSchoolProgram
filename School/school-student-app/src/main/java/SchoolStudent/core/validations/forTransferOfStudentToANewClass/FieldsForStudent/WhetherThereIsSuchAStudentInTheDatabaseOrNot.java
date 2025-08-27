package SchoolStudent.core.validations.forTransferOfStudentToANewClass.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.forTransferOfStudentToANewClass.TransferOfStudentToANewClassRequestFieldsValidationImpl;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class WhetherThereIsSuchAStudentInTheDatabaseOrNot extends TransferOfStudentToANewClassRequestFieldsValidationImpl {

    @Autowired private ValidatorClassWithMethodsForParametersEmailAndPassword validation;

    @Override
    public Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        return validation.whetherThereIsSuchAStudentInTheDatabaseOrNot(request.getEmail());
    }


}
