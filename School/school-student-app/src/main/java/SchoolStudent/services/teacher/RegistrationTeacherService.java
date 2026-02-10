package SchoolStudent.services.teacher;

import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.domain.Teacher;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.jpa.repositories.TeacherRepository;
import SchoolStudent.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.response.teacher.RegistrationTeacherResponse;
import SchoolStudent.util.ValidationError;
import SchoolStudent.validations.teacher.RegistrationTeacherValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class RegistrationTeacherService {

    private final RegistrationTeacherValidator validator;
    private final TeacherRepository repository;
    private final SchoolClassRepository schoolClassRepository;

    @Transactional
    public RegistrationTeacherResponse execute(RegistrationTeacherRequest request) {
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()){
           return RegistrationTeacherResponse.builder().errors(validationErrors).build();
        }
        repository.save(buildTeacher(request));
        return RegistrationTeacherResponse.builder().message("You registered in database").build();
    }

    private Teacher buildTeacher(RegistrationTeacherRequest request){
        SchoolClass schoolClass = schoolClassRepository.findByNumberAndCategory(request.getTeacherDTO().getSchoolClassDTO().getNumber(),
                request.getTeacherDTO().getSchoolClassDTO().getCategory()).get();
        return Teacher.builder()
                .firstName(request.getTeacherDTO().getFirstName())
                .lastName(request.getTeacherDTO().getLastName())
                .fatherland(request.getTeacherDTO().getFatherland())
                .email(request.getTeacherDTO().getEmail())
                .password(request.getTeacherDTO().getPassword())
                .subject(request.getTeacherDTO().getSubject())
                .schoolClass(schoolClass).build();
    }
}
