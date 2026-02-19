package SchoolStudent.services.schoolStudent;

import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.domain.SchoolStudent;
import SchoolStudent.jpa.dto.SchoolStudentDTO;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.jpa.repositories.SchoolStudentRepository;
import SchoolStudent.request.student.RegistrationStudentRequest;
import SchoolStudent.response.student.RegistrationStudentResponse;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.controllers.CreateCertificatesRestController;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.request.CreateCertificatesRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.response.CreateCertificatesResponse;
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
    private final CreateCertificatesRestController microservice;

    @Transactional
    public RegistrationStudentResponse execute(RegistrationStudentRequest request) {
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return RegistrationStudentResponse.builder().errors(validationErrors).build();
        }
        SchoolClass schoolClass =
                getSchoolClass(request);
        Long studentId = repository.save(buildSchoolStudent(request, schoolClass)).getId();
        CreateCertificatesResponse response = sendToMicroservice(studentId, schoolClass);
        if (response.hasErrors()){
            repository.deleteById(studentId);
            return RegistrationStudentResponse.builder().errors(response.getErrors()).build();
        }
        return RegistrationStudentResponse
                .builder()
                .message("Student successfully registered. " + response.getMessage())
                .build();
    }

    private CreateCertificatesResponse sendToMicroservice(Long studentId, SchoolClass schoolClass) {
        return microservice.execute(CreateCertificatesRequest.builder().
                studentId(studentId).
                schoolClassId(schoolClass.getId()).build());
    }

    private SchoolClass getSchoolClass(RegistrationStudentRequest request) {
        return repository2.findByNumberAndCategory(
                request.getSchoolStudentDTO()
                        .getSchoolClassDTO()
                        .getNumber(),
                request.getSchoolStudentDTO()
                        .getSchoolClassDTO()
                        .getCategory()
        ).orElseThrow();
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
