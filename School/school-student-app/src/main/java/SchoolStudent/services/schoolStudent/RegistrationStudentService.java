package SchoolStudent.services.schoolStudent;

import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.domain.SchoolStudent;
import SchoolStudent.jpa.dto.SchoolStudentDTO;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.jpa.repositories.SchoolStudentRepository;
import SchoolStudent.request.student.RegistrationStudentRequest;
import SchoolStudent.response.student.RegistrationStudentResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.student.RegistrationStudentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationStudentService {

    private final SchoolStudentRepository repository;
    private final RegistrationStudentValidator validator;
    private final SchoolClassRepository repository2;

    @Transactional
    public RegistrationStudentResponse execute(RegistrationStudentRequest request){
        List<ValidationError> validationErrors = validator.validationErrorsList(request);
        if (!validationErrors.isEmpty()){
            return RegistrationStudentResponse.builder().errors(validationErrors).build();
        }else {
            SchoolClass schoolClass =
                    repository2.findByNumberAndCategory(
                            request.getSchoolStudentDTO()
                                    .getSchoolClassDTO()
                                    .getNumber(),
                            request.getSchoolStudentDTO()
                                    .getSchoolClassDTO()
                                    .getCategory()
                    ).orElseThrow();
            repository.save(buildSchoolStudent(request,schoolClass));
        }
        return RegistrationStudentResponse
                .builder()
                .message("Student successfully registered.")
                .build();
    }

    private SchoolStudent buildSchoolStudent(RegistrationStudentRequest request,SchoolClass schoolClass){

        SchoolStudentDTO dto = request.getSchoolStudentDTO();
        return SchoolStudent.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .fatherland(dto.getFatherland())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .schoolClass(schoolClass)
                .build();
    }
}
