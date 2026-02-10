package SchoolStudent.validations.schoolClass;

import SchoolStudent.request.schoolClass.CreateSchoolClassRequest;
import SchoolStudent.util.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateSchoolClassValidator {

    private final ValidatorClassWithMethodsForSchoolClassParameters validatorClassWithMethodsForSchoolClassParameters;

    public List<ValidationError> validate(CreateSchoolClassRequest request) {
        List<ValidationError> errorDTOS = new ArrayList<>();
        validateSchoolClassParameters(errorDTOS,request);
        return errorDTOS;
    }

    private void validateSchoolClassParameters(List<ValidationError> errorDTOS, CreateSchoolClassRequest request) {
        validatorClassWithMethodsForSchoolClassParameters.numberMustNotBeEmpty(request.getSchoolClassDTO().getNumber()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForSchoolClassParameters.categoryMustNotBeEmpty(request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForSchoolClassParameters.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            if (errorDTOS.isEmpty()){
                validatorClassWithMethodsForSchoolClassParameters.suchSchoolClassAlreadyExist(request.getSchoolClassDTO().getNumber(),request.getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            }
        }
    }
}
