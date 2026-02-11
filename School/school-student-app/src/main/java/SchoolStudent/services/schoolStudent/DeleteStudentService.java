package SchoolStudent.services.schoolStudent;


import SchoolStudent.jpa.domain.SchoolStudent;
import SchoolStudent.jpa.repositories.SchoolStudentRepository;
import SchoolStudent.request.student.DeleteStudentRequest;
import SchoolStudent.response.student.DeleteStudentResponse;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.controllers.LessonsAndCertificatesMicroserviceDeleteStudents;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.request.DeleteStudentsLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.response.DeleteStudentsLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.student.DeleteStudentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteStudentService {

    private final SchoolStudentRepository repository;
    private final DeleteStudentValidator validator;
    private final LessonsAndCertificatesMicroserviceDeleteStudents lessonsAndCertificatesMicroserviceDeleteStudents;

    @Transactional
    public DeleteStudentResponse execute(DeleteStudentRequest request){
        DeleteStudentsLessonsAndCertificatesMicroserviceResponse microserviceResponse;
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()){
            return DeleteStudentResponse.builder().errors(validationErrors).build();
        }else {
            List<SchoolStudent> students = getStudents(request);
            microserviceResponse = lessonsAndCertificatesMicroserviceDeleteStudents.
                            execute(DeleteStudentsLessonsAndCertificatesMicroserviceRequest.builder().studentIds(getStudentIds(students)).build());
            deleteStudents(students);
        }
        return DeleteStudentResponse.builder().message(microserviceResponse.getMessage()).build();
    }

    private void deleteStudents(List<SchoolStudent> students) {
        students.forEach(student -> repository.deleteByEmail(student.getEmail()));
    }

    private List<Long> getStudentIds(List<SchoolStudent> students){
        return students.stream()
                .map(SchoolStudent::getId)
                .toList();
    }

    private List<SchoolStudent> getStudents(DeleteStudentRequest request){
        return Optional.ofNullable(request.getEmails())
                .orElse(Collections.emptyList())
                .stream()
                .map(repository::findByEmail)
                .flatMap(Optional::stream)
                .toList();
    }
}
