package SchoolStudent.core.validations.forTransferOfStudentToANewClass.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.forTransferOfStudentToANewClass.TransferOfStudentToANewClassRequestFieldsValidationImpl;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmailForTransferValidation extends TransferOfStudentToANewClassRequestFieldsValidationImpl {

    @Autowired private ValidatorClassWithMethodsForParametersEmailAndPassword validation;

    @Override
    public Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        if (validation.mustNotBeEmptyEmail(request.getEmail()).isPresent()){
            return validation.mustNotBeEmptyEmail(request.getEmail());
        }else if (validation.emailYourEmailIsNotCorrectError(request.getEmail()).isPresent()){
            return validation.emailYourEmailIsNotCorrectError(request.getEmail());
        }
        return Optional.empty();
    }

}
