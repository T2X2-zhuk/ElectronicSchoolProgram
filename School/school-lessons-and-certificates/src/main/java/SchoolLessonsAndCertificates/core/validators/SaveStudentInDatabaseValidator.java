package SchoolLessonsAndCertificates.core.validators;

import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.request.SaveStudentRequest;
import SchoolLessonsAndCertificates.core.validators.saveStudent.SaveStudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SaveStudentInDatabaseValidator {

    @Autowired
    private List<SaveStudentValidation> validations;

    public List<ValidationErrorDTO> validate(SaveStudentRequest request) {
        return collectListStudentErrors(request);
    }

    private List<ValidationErrorDTO> collectListStudentErrors(SaveStudentRequest request) {
        return validations.stream()
                .map(validation -> validation.validationErrorDTOSList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
