package SchoolLessonsAndCertificates.core.validators.getLessonByName;

import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.request.GettingTheNameOfAnExistingLessonRequest;
import SchoolLessonsAndCertificates.core.validators.MethodsValidatorClasses.ValidatorClassWithMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IsLessonInDatabaseOfAnExistingLesson implements GettingTheNameOfAnExistingLessonValidation {
    @Autowired private ValidatorClassWithMethods methods;

    @Override
    public List<ValidationErrorDTO> validationErrorDTOSList(GettingTheNameOfAnExistingLessonRequest request) {
        List<ValidationErrorDTO>  errorDTOS = new ArrayList<>();
        methods.isLessonInDatabaseError(request.getLessonName()).ifPresent(errorDTOS ::add);
        return errorDTOS;
    }
}
