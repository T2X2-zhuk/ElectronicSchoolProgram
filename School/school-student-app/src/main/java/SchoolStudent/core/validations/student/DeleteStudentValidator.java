package SchoolStudent.core.validations.student;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public
class DeleteStudentValidator {

    @Autowired private ValidatorClassWithMethodsForParametersEmailAndPassword validation;

    public List<ValidationErrorDTO> validate(DeleteStudentFromDatabaseRequest request) {
        List<ValidationErrorDTO> errors = collectStudentErrors(request);
        return errors;
    }

    private List<ValidationErrorDTO> collectStudentErrors(DeleteStudentFromDatabaseRequest request){
        List<ValidationErrorDTO> errors = new ArrayList<>();
        validation.notEnteredASingleCorrectPassword(request.getPasswords()).ifPresent(errors::add);
        return errors;
    }
}
