package SchoolLessonsAndCertificates.services.classLesson;

import SchoolLessonsAndCertificates.jpa.database.ClassLessonRepository;
import SchoolLessonsAndCertificates.jpa.database.LessonRepository;
import SchoolLessonsAndCertificates.jpa.domain.ClassLesson;
import SchoolLessonsAndCertificates.jpa.domain.Lesson;
import SchoolLessonsAndCertificates.request.classLesson.CreateClassLessonsRequest;
import SchoolLessonsAndCertificates.response.CreateClassLessonsResponse;
import SchoolLessonsAndCertificates.util.ValidationError;
import SchoolLessonsAndCertificates.validators.classLesson.CreateClassLessonsValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateClassLessonsService {

    private final CreateClassLessonsValidator validator;
    private final LessonRepository lessonRepository;
    private final ClassLessonRepository classLessonRepository;

    @Transactional
    public CreateClassLessonsResponse execute(CreateClassLessonsRequest request){
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return CreateClassLessonsResponse.builder().errors(errors).build();
        }
        Lesson lesson = lessonRepository
                .findByName(request.getLessonDTO().getName()).orElseThrow();
        List<Long> filteredIds = filterSchoolClassIds(request.getSchoolClassIds(),lesson.getName());
        if (filteredIds.isEmpty()) {
            return CreateClassLessonsResponse.builder()
                    .message("All selected classes already contain this lesson.")
                    .build();
        }
        return saveAllClassLessons(filteredIds, lesson);
    }

    private CreateClassLessonsResponse saveAllClassLessons(List<Long> filteredIds, Lesson lesson) {
        List<ClassLesson> classLessons = filteredIds.stream()
                .map(classId -> ClassLesson.builder()
                        .schoolClassId(classId)
                        .lesson(lesson)
                        .build()
                )
                .toList();
        classLessonRepository.saveAll(classLessons);
        return CreateClassLessonsResponse.builder()
                .message("Class lessons successfully created.")
                .build();
    }

    private List<Long> filterSchoolClassIds(List<Long> schoolClassIds,String lessonName){
        return schoolClassIds.stream()
                .filter(classId ->
                        !classLessonRepository
                                .existsBySchoolClassIdAndLesson_Name(classId, lessonName)
                )
                .toList();
    }
}
