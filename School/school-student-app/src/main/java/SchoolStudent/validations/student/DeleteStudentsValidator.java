package SchoolStudent.validations.student;

import SchoolStudent.request.student.DeleteStudentsRequest;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolStudentParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteStudentsValidator {

    private final ValidatorClassWithMethodsForSchoolStudentParameters validation;

    public List<ValidationError> validate(DeleteStudentsRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validation.areThereAnyEmailAddressesOfStudentsOnTheList(request.getEmails()).ifPresent(errors::add);
        return errors;
    }
}
