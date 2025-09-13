package SchoolStudent.core.service.services;


import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForDeleteStudents;
import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.request.student.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.response.student.DeleteStudentFromDatabaseResponse;
import SchoolStudent.core.service.interfeicesForServices.DeleteStudentFromDatabaseService;
import SchoolStudent.core.validations.student.DeleteStudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
class DeleteStudentFromDatabaseServiceImpl implements DeleteStudentFromDatabaseService {

    @Autowired private SchoolStudentRepository repository;
    @Autowired private DeleteStudentValidator validator;
    @Autowired private SchoolLessonsAndCertificatesMicroserviceForDeleteStudents schoolLessonsAndCertificatesMicroserviceServiceImplForDeleteStudents;

    @Override
    public DeleteStudentFromDatabaseResponse  execute(DeleteStudentFromDatabaseRequest request){
        return validateRequest(request);
    }

    private DeleteStudentFromDatabaseResponse validateRequest(DeleteStudentFromDatabaseRequest request){
        DeleteStudentFromDatabaseResponse response = new DeleteStudentFromDatabaseResponse();
        if (!validator.validate(request).isEmpty()){
            response.setErrors(validator.validate(request));
        }else {
            DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse microserviceResponse = schoolLessonsAndCertificatesMicroserviceServiceImplForDeleteStudents.execute(buildMicroserviceRequest(request));
            response.setMessage(microserviceResponse.getMessage());
            successfulDelete(request);
        }
        return response;
    }
    private void successfulDelete(DeleteStudentFromDatabaseRequest request){
        List<String> passwordFilter = Optional.ofNullable(request.getPasswords())
                .orElse(Collections.emptyList()) // если null → пустой список
                .stream()
                .filter(password -> repository.findBypassword(password).isPresent())
                .toList();
        passwordFilter.forEach(password -> repository.deleteByPassword(password));
    }

    private DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest buildMicroserviceRequest(DeleteStudentFromDatabaseRequest request){
        return new DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest(getNeedEmailsWithStudents(request));
    }

    private List<String> getNeedEmailsWithStudents(DeleteStudentFromDatabaseRequest request){
        List<SchoolStudent> students = Optional.ofNullable(request.getPasswords())
                .orElse(Collections.emptyList())
                .stream()
                .map(repository::findBypassword)   // получаем Optional<SchoolStudent>
                .flatMap(Optional::stream)         // "распаковываем" Optional
                .toList();
        return students.stream()
                .map(SchoolStudent::getEmail) // берём email у каждого студента
                .toList();
    }
}
