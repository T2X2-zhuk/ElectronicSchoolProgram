package SchoolStudent.core.validations.student.Transfer;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersEmailAndPassword;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
 class TransferStudentValidationNumberEmailCategoryFields implements TransferStudentValidation {

     @Autowired private ValidatorClassWithMethodsForParametersEmailAndPassword validation;
    @Autowired private ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation2;
    @Override
    public List<ValidationErrorDTO> validateList(TransferOfStudentToANewClassRequest request) {
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validateEmail(request).ifPresent(errorDTOS::add);
        validateNumber(request).ifPresent(errorDTOS::add);
        validateSuchCategoryClassIsNotInDatabase(request).ifPresent(errorDTOS::add);
        validateIsSuchAStudentInTheDatabaseOrNot(request).ifPresent(errorDTOS::add);
        return errorDTOS;
    }

    private Optional<ValidationErrorDTO> validateEmail(TransferOfStudentToANewClassRequest request){
        if (validation.mustNotBeEmptyEmail(request.getEmail()).isPresent()){
            return validation.mustNotBeEmptyEmail(request.getEmail());
        }else if (validation.emailYourEmailIsNotCorrectError(request.getEmail()).isPresent()){
            return validation.emailYourEmailIsNotCorrectError(request.getEmail());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateNumber(TransferOfStudentToANewClassRequest request) {
        if (validation2.mustNotBeEmptyNumber(request.getNewClassNumber()).isPresent()){
            return validation2.mustNotBeEmptyNumber(request.getNewClassNumber());
        }if (validation2.notSuchSchoolClass(request.getNewClassNumber()).isPresent()){
            return validation2.notSuchSchoolClass(request.getNewClassNumber());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateIsSuchAStudentInTheDatabaseOrNot(TransferOfStudentToANewClassRequest request) {
        return validation.whetherThereIsSuchAStudentInTheDatabaseOrNot(request.getEmail());
    }

    private Optional<ValidationErrorDTO> validateSuchCategoryClassIsNotInDatabase(TransferOfStudentToANewClassRequest request) {
        if ( validation2.categoryClassMustNotBeEmpty(request.getCategory()).isPresent()){
            return validation2.categoryClassMustNotBeEmpty(request.getCategory());
        } else if (validation2.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory()).isPresent()){
            return validation2.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getCategory());
        } else if (validation2.suchCategoryClassIsNotInDatabase(request.getCategory()).isPresent()){
            return validation2.suchCategoryClassIsNotInDatabase(request.getCategory());
        }
        return Optional.empty();
    }
}
