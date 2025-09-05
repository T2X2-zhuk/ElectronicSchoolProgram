package SchoolLessonsAndCertificates.core.validators.saveStudent;

import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.request.SaveStudentRequest;

import java.util.List;

public interface SaveStudentValidation {

    List<ValidationErrorDTO> validationErrorDTOSList(SaveStudentRequest request);
}
