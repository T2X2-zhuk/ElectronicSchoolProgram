package SchoolStudent.core.validations.forTransferOfStudentToANewClass.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.forTransferOfStudentToANewClass.TransferOfStudentToANewClassRequestFieldsValidationImpl;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 class FieldNewNumberClassForTransferValidation extends TransferOfStudentToANewClassRequestFieldsValidationImpl
{

    @Autowired private ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation ;
    @Override
    public Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        if (validation.mustNotBeEmptyNumber(request.getNewClassNumber()).isPresent()){
            return validation.mustNotBeEmptyNumber(request.getNewClassNumber());
        }if (validation.notSuchSchoolClass(request.getNewClassNumber()).isPresent()){
            return validation.notSuchSchoolClass(request.getNewClassNumber());
        }
        return Optional.empty();
    }

}
