package SchoolStudent.services.schoolStudent;


import SchoolStudent.jpa.domain.SchoolStudent;
import SchoolStudent.jpa.repositories.SchoolStudentRepository;
import SchoolStudent.request.student.DeleteStudentsRequest;
import SchoolStudent.response.student.DeleteStudentsResponse;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.controllers.DeleteStudentCertificatesRestController;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.request.DeleteStudentCertificatesRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.response.DeleteStudentCertificatesResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.student.DeleteStudentsValidator;
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
public class DeleteStudentsService {

    private final SchoolStudentRepository repository;
    private final DeleteStudentsValidator validator;
    private final DeleteStudentCertificatesRestController deleteStudentCertificatesRestController;

    @Transactional
    public DeleteStudentsResponse execute(DeleteStudentsRequest request){
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()){
            return DeleteStudentsResponse.builder().errors(validationErrors).build();
        }
        List<SchoolStudent> students = getStudents(request);
        DeleteStudentCertificatesResponse microserviceResponse = deleteStudentCertificatesRestController.
                execute(DeleteStudentCertificatesRequest.builder().studentIds(getStudentIds(students)).build());
        deleteStudents(students);
        return DeleteStudentsResponse.builder().message(microserviceResponse.getMessage()).build();
    }

    private void deleteStudents(List<SchoolStudent> students) {
        students.forEach(student -> repository.deleteByEmail(student.getEmail()));
    }

    private List<Long> getStudentIds(List<SchoolStudent> students){
        return students.stream()
                .map(SchoolStudent::getId)
                .toList();
    }

    private List<SchoolStudent> getStudents(DeleteStudentsRequest request){
        return Optional.ofNullable(request.getEmails())
                .orElse(Collections.emptyList())
                .stream()
                .map(repository::findByEmail)
                .flatMap(Optional::stream)
                .toList();
    }
}
