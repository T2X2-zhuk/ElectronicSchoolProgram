package SchoolStudent.validations.schoolClass;

import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetSchoolClassIdsValidator {

    private final ValidatorClassWithMethodsForSchoolClassParameters validatorClassWithMethodsForSchoolClassParameters;

    public List<ValidationError> validate(List<Long> schoolClassIds) {
        List<ValidationError> errorDTOS = new ArrayList<>();
        validatorClassWithMethodsForSchoolClassParameters.validateSchoolClassIds(schoolClassIds).ifPresent(errorDTOS::add);
        return errorDTOS;
    }
}
