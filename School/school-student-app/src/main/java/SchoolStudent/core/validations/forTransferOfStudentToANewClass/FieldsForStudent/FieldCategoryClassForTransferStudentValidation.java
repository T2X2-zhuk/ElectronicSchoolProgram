package SchoolStudent.core.validations.forTransferOfStudentToANewClass.FieldsForStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.forTransferOfStudentToANewClass.TransferOfStudentToANewClassRequestFieldsValidationImpl;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FieldCategoryClassForTransferStudentValidation extends TransferOfStudentToANewClassRequestFieldsValidationImpl {

    @Autowired private ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation;

    @Override
    public Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        if ( validation.categoryClassMustNotBeEmpty(request.getCategory()).isPresent()){
            return validation.categoryClassMustNotBeEmpty(request.getCategory());
        } else if (validation.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory()).isPresent()){
            return validation.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory());
        } else if (validation.suchCategoryClassIsNotInDatabase(request.getCategory()).isPresent()){
            return validation.suchCategoryClassIsNotInDatabase(request.getCategory());
        }
        return Optional.empty();
    }

}
