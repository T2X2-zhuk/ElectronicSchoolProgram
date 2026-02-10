package SchoolLessonsAndCertificates.services;

import SchoolLessonsAndCertificates.jpa.database.LessonRepository;
import SchoolLessonsAndCertificates.jpa.domain.Lesson;
import SchoolLessonsAndCertificates.restAPI.controllers.fromOtherMicroservicesRequest.GetLessonNameRequest;
import SchoolLessonsAndCertificates.restAPI.controllers.fromOtherMicroservicesResponse.GetLessonNameResponse;
import SchoolLessonsAndCertificates.util.ValidationError;
import SchoolLessonsAndCertificates.validators.GetLessonNameValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetLessonNameService {

    private final GetLessonNameValidator validator;
    private final LessonRepository repository;

    @Transactional
    public GetLessonNameResponse execute(GetLessonNameRequest request){
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return GetLessonNameResponse.builder().errors(errors).build();
        }
        Lesson lesson = repository.findByName(request.getLessonName()).get();
        return GetLessonNameResponse.builder().lessonName(lesson.getName()).build();
    }
}
