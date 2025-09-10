package SchoolLessonsAndCertificates.core.validators.getLessonByName;

import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.request.GettingTheNameOfAnExistingLessonRequest;

import java.util.List;

public interface GettingTheNameOfAnExistingLessonValidation {

    List<ValidationErrorDTO> validationErrorDTOSList(GettingTheNameOfAnExistingLessonRequest request);
}
