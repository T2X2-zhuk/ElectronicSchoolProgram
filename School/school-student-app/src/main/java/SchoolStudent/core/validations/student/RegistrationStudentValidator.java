package SchoolStudent.core.validations.student;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.student.Registration.RegistrationStudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.stream.Collectors;

@Component
public class RegistrationStudentValidator {
    @Autowired
    private List<RegistrationStudentValidation> fieldForRegistrationStudentValidations;

    public List<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        return collectListStudentErrors(request);
    }

    private List<ValidationErrorDTO> collectListStudentErrors(RegistrationStudentInDatabaseRequest request) {
        return fieldForRegistrationStudentValidations.stream()
                .map(validation -> validation.validationErrorDTOSList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
