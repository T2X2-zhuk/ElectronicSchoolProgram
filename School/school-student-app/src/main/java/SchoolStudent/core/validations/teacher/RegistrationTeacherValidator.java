package SchoolStudent.core.validations.teacher;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.core.validations.student.Registration.RegistrationStudentValidation;
import SchoolStudent.core.validations.teacher.Registration.RegistrationTeacherValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RegistrationTeacherValidator {
    @Autowired
    private List<RegistrationTeacherValidation> fieldForRegistrationStudentValidations;

    public List<ValidationErrorDTO> validate(RegistrationTeacherRequest request) {
        return collectListStudentErrors(request);
    }

    private List<ValidationErrorDTO> collectListStudentErrors(RegistrationTeacherRequest request) {
        return fieldForRegistrationStudentValidations.stream()
                .map(validation -> validation.validationErrorDTOSList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
