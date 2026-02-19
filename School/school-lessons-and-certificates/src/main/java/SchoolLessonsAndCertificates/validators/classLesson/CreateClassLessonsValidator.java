package SchoolLessonsAndCertificates.validators.classLesson;

import SchoolLessonsAndCertificates.request.classLesson.CreateClassLessonsRequest;
import SchoolLessonsAndCertificates.restAPI.microservice.schoolStudent.controllers.GetSchoolClassIdsRestController;
import SchoolLessonsAndCertificates.restAPI.microservice.schoolStudent.request.GetSchoolClassIdsRequest;
import SchoolLessonsAndCertificates.restAPI.microservice.schoolStudent.response.GetSchoolClassIdsResponse;
import SchoolLessonsAndCertificates.util.ValidationError;
import SchoolLessonsAndCertificates.validators.MethodsValidatorClasses.ValidatorClassWithMethodsForClassLessonParameters;
import SchoolLessonsAndCertificates.validators.MethodsValidatorClasses.ValidatorClassWithMethodsForLessonParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateClassLessonsValidator {

    private final ValidatorClassWithMethodsForLessonParameters validatorClassWithMethodsForLesson;
    private final GetSchoolClassIdsRestController microserviceGetStudentsIds;
    private final ValidatorClassWithMethodsForClassLessonParameters validatorClassWithMethodsForClassLessonParameters;

    public List<ValidationError> validate(CreateClassLessonsRequest request){
        List<ValidationError> errors = new ArrayList<>();
        validatorClassWithMethodsForLesson.lessonNameMustNotBeEmpty(request.getLessonDTO().getName()).ifPresent(errors ::add);
        validatorClassWithMethodsForClassLessonParameters.validateListSchoolClassDTOs(request.getSchoolClassDTOS()).ifPresent(errors ::add);
        if (errors.isEmpty()){
            validatorClassWithMethodsForLesson.isLessonInDatabaseError(request.getLessonDTO().getName()).ifPresent(errors ::add);
            if (errors.isEmpty()) {
                sendDTOsToMicroservice(request, errors);
            }
        }
        return errors;
    }

    private void sendDTOsToMicroservice(CreateClassLessonsRequest request, List<ValidationError> errors) {
        GetSchoolClassIdsResponse response = microserviceGetStudentsIds.execute(
                GetSchoolClassIdsRequest.builder()
                        .schoolClassDTOS(request.getSchoolClassDTOS()).build());
        if (response.hasErrors()) {
            errors.addAll(response.getErrors());

        }else{
            request.setSchoolClassIds(response.getStudentsIds());
        }
    }
}
