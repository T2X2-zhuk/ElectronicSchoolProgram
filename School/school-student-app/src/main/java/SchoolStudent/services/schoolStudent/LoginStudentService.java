package SchoolStudent.services.schoolStudent;

import SchoolStudent.jpa.domain.SchoolStudent;
import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.jpa.dto.SchoolStudentDTO;
import SchoolStudent.jpa.repositories.SchoolStudentRepository;
import SchoolStudent.request.student.LoginStudentRequest;
import SchoolStudent.response.student.LoginStudentResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.student.LoginStudentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginStudentService {

    private final SchoolStudentRepository repository;
    private final LoginStudentValidator validator;

    @Transactional
    public LoginStudentResponse execute(LoginStudentRequest request) {
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return LoginStudentResponse.builder().errors(errors).build();
        }
        return LoginStudentResponse.builder().schoolStudentDTO(getSchoolStudent(request)).build();
    }

    private SchoolStudentDTO getSchoolStudent(LoginStudentRequest request) {
        SchoolStudent schoolStudent = repository.findByPassword(request.getPassword()).orElseThrow();
        SchoolClassDTO schoolClassDTO = SchoolClassDTO.builder()
                .number(schoolStudent.getSchoolClass().getNumber())
                .category(schoolStudent.getSchoolClass().getCategory()).build();
        return SchoolStudentDTO.builder()
                .firstName(schoolStudent.getFirstName())
                .lastName(schoolStudent.getLastName())
                .fatherland(schoolStudent.getFatherland())
                .email(schoolStudent.getEmail())
                .password(schoolStudent.getPassword())
                .schoolClassDTO(schoolClassDTO).build();
    }
}
