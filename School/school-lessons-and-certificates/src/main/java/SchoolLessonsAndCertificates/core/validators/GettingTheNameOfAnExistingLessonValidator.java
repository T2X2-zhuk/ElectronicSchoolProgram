package SchoolLessonsAndCertificates.core.validators;

import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.request.GettingTheNameOfAnExistingLessonRequest;
import SchoolLessonsAndCertificates.core.validators.getLessonByName.GettingTheNameOfAnExistingLessonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GettingTheNameOfAnExistingLessonValidator {

    @Autowired
    private List<GettingTheNameOfAnExistingLessonValidation> validations;

    public List<ValidationErrorDTO> validate(GettingTheNameOfAnExistingLessonRequest request) {
        return collectListStudentErrors(request);
    }

    private List<ValidationErrorDTO> collectListStudentErrors(GettingTheNameOfAnExistingLessonRequest request) {
        return validations.stream()
                .map(validation -> validation.validationErrorDTOSList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
