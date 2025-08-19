package SchoolStudent.core.validations.forTransferOfStudentToANewClass.FieldsForStudent;

import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.ValidationErrorFactory;
import SchoolStudent.core.validations.forTransferOfStudentToANewClass.TransferOfStudentToANewClassRequestFieldsValidationImpl;
import SchoolStudent.core.validations.validatorMethodsFactory.ValidationMethodsFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 class FieldNewNumberClassForTransferValidation extends TransferOfStudentToANewClassRequestFieldsValidationImpl
{

    @Autowired private ValidationMethodsFactory validationMethodsFactory;
    @Override
    public Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode validation = validationMethodsFactory.getValidatorsMethodsList().stream()
                .filter(validator -> validator instanceof ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode)
                .map(validator -> (ValidatorClassWithMethodsForParametersNumberAndCategorySpecialCode) validator)
                .findFirst()
                .orElse(null);
        assert validation != null;
        if (validation.mustNotBeEmptyNumber(request.getNewClassNumber()).isPresent()){
            return validation.mustNotBeEmptyNumber(request.getNewClassNumber());
        }if (validation.notSuchSchoolClass(request.getNewClassNumber()).isPresent()){
            return validation.notSuchSchoolClass(request.getNewClassNumber());
        }
        return Optional.empty();
    }

}
