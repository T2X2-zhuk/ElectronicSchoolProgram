package SchoolLessonsAndCertificates.validators.lesson;

import SchoolLessonsAndCertificates.request.lesson.GetLessonNameRequest;
import SchoolLessonsAndCertificates.util.ValidationError;
import SchoolLessonsAndCertificates.validators.MethodsValidatorClasses.ValidatorClassWithMethodsForLessonParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLessonNameValidator {

    private final ValidatorClassWithMethodsForLessonParameters methods;

    public List<ValidationError> validate(GetLessonNameRequest request) {
        List<ValidationError>  errorDTOS = new ArrayList<>();
        methods.isLessonInDatabaseError(request.getLessonName()).ifPresent(errorDTOS ::add);
        return errorDTOS;
    }
}
