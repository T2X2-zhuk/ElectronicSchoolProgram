package SchoolLessonsAndCertificates.validators;

import SchoolLessonsAndCertificates.restAPI.controllers.fromOtherMicroservicesRequest.GetLessonNameRequest;
import SchoolLessonsAndCertificates.util.ValidationError;
import SchoolLessonsAndCertificates.validators.MethodsValidatorClasses.ValidatorClassWithMethodsForLesson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLessonNameValidator {

    private final ValidatorClassWithMethodsForLesson methods;

    public List<ValidationError> validate(GetLessonNameRequest request) {
        List<ValidationError>  errorDTOS = new ArrayList<>();
        methods.isLessonInDatabaseError(request.getLessonName()).ifPresent(errorDTOS ::add);
        return errorDTOS;
    }
}
