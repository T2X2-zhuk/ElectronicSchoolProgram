package SchoolStudent.validations.student;

import SchoolStudent.request.student.TransferStudentToNewClassRequest;
import SchoolStudent.util.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolStudentParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransferStudentToNewClassValidator {

    private final ValidatorClassWithMethodsForSchoolStudentParameters validatorClassWithMethodsForSchoolStudentParameters;
    private final ValidatorClassWithMethodsForSchoolClassParameters validatorClassWithMethodsForSchoolClassParameters;

    public List<ValidationError> validate(TransferStudentToNewClassRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validateEmail(errors,request);
        validateSchoolClassParameters(errors,request);
        return errors;
    }

    private void validateSchoolClassParameters(List<ValidationError> errorDTOS, TransferStudentToNewClassRequest request) {
        validatorClassWithMethodsForSchoolClassParameters.numberMustNotBeEmpty(request.getSchoolClassDTO().getNumber()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForSchoolClassParameters.categoryMustNotBeEmpty(request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForSchoolClassParameters.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            if (errorDTOS.isEmpty()){
                validatorClassWithMethodsForSchoolClassParameters.suchSchoolClassIsNotExist(request.getSchoolClassDTO().getNumber(),request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            }
        }
    }

    private void validateEmail(List<ValidationError> errors, TransferStudentToNewClassRequest request) {
        validatorClassWithMethodsForSchoolStudentParameters.mustNotBeEmptyEmail(request.getEmail()).ifPresent(errors::add);
        if (errors.isEmpty()){
            validatorClassWithMethodsForSchoolStudentParameters.areThereAnyEmailAddressesOfStudentsOnTheList(List.of(request.getEmail())).ifPresent(errors::add);
        }
    }
}
