package SchoolStudent.validations.student;

import SchoolStudent.request.student.GetStudentsBySchoolClassRequest;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
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
public class GetStudentsBySchoolClassValidator {

    private final ValidatorClassWithMethodsForSchoolClassParameters validatorClassWithMethodsForSchoolClassParameters;
    private final ValidatorClassWithMethodsForSchoolStudentParameters validationStudents;

    public List<ValidationError> validationErrorDTOSList(GetStudentsBySchoolClassRequest request) {
        List<ValidationError> errorDTOS = new ArrayList<>();
        validateSchoolClassParameters(errorDTOS,request);
        validationStudents.checkStudentsExistInClass(request.getSchoolClassDTO().getNumber(), request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
        return errorDTOS;
    }

    private void validateSchoolClassParameters(List<ValidationError> errorDTOS, GetStudentsBySchoolClassRequest request) {
        validatorClassWithMethodsForSchoolClassParameters.numberMustNotBeEmpty(request.getSchoolClassDTO().getNumber()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForSchoolClassParameters.categoryMustNotBeEmpty(request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForSchoolClassParameters.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            if (errorDTOS.isEmpty()){
                validatorClassWithMethodsForSchoolClassParameters.suchSchoolClassIsNotExist(request.getSchoolClassDTO().getNumber(),request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            }
        }
    }
}
