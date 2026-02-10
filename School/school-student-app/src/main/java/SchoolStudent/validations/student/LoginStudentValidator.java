package SchoolStudent.validations.student;

import SchoolStudent.request.student.LoginStudentRequest;
import SchoolStudent.util.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolStudentParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginStudentValidator {

    private final ValidatorClassWithMethodsForSchoolStudentParameters validation;

    public List<ValidationError> validate(LoginStudentRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validation.mustNotBeEmptyPassword(request.getPassword()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validation.suchStudentIsNotExists(request.getPassword()).ifPresent(errors::add);
        }
        return errors;
    }
}
