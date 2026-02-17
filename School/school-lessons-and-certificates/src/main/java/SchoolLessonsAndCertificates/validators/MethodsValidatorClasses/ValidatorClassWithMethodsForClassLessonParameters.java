package SchoolLessonsAndCertificates.validators.MethodsValidatorClasses;

import SchoolLessonsAndCertificates.jpa.database.ClassLessonRepository;
import SchoolLessonsAndCertificates.jpa.dto.SchoolClassDTO;
import SchoolLessonsAndCertificates.util.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidatorClassWithMethodsForClassLessonParameters {

    private final ValidationErrorFactory errorFactory;
    private final ClassLessonRepository classLessonRepository;

    public Optional<ValidationError> validateListSchoolClassDTOs(List<SchoolClassDTO> schoolClassDTOS){
        return (schoolClassDTOS.isEmpty())
                ? Optional.of(errorFactory.buildError("LESSONS_AND_CERTIFICATES_ERROR_CODE_3"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateFindClassLessonsBySchoolClassId(Long schoolClassId){
        return (!classLessonRepository.existsBySchoolClassId(schoolClassId))
                ? Optional.of(errorFactory.buildError("LESSONS_AND_CERTIFICATES_ERROR_CODE_5"))
                : Optional.empty();
    }
}
