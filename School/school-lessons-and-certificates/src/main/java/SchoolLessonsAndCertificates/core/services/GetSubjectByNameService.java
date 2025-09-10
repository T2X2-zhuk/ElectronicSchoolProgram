package SchoolLessonsAndCertificates.core.services;

import SchoolLessonsAndCertificates.core.database.LessonRepository;
import SchoolLessonsAndCertificates.core.domain.Lesson;
import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.request.GettingTheNameOfAnExistingLessonRequest;
import SchoolLessonsAndCertificates.core.request.SaveStudentRequest;
import SchoolLessonsAndCertificates.core.response.GettingTheNameOfAnExistingLessonResponse;
import SchoolLessonsAndCertificates.core.response.SaveStudentResponse;
import SchoolLessonsAndCertificates.core.validators.GettingTheNameOfAnExistingLessonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GetSubjectByNameService {

    @Autowired private GettingTheNameOfAnExistingLessonValidator validator;

    @Autowired private LessonRepository repository;
    public GettingTheNameOfAnExistingLessonResponse execute(GettingTheNameOfAnExistingLessonRequest request){
        return validateRequestAndReturnResult(request);
    }
    private GettingTheNameOfAnExistingLessonResponse validateRequestAndReturnResult(GettingTheNameOfAnExistingLessonRequest request){
        GettingTheNameOfAnExistingLessonResponse response = new GettingTheNameOfAnExistingLessonResponse();
        if (!validator.validate(request).isEmpty()){
            response.setErrors(validator.validate(request));
        }else {
            Optional<Lesson> lesson = repository.findByName(request.getLessonName());
            response.setLessonName(lesson.get().getName());
        }
        return response;
    }
}
