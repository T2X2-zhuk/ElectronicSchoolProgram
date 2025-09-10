package SchoolStudent.core.service;

import SchoolStudent.core.SchoolLessonsAndCertificates.SchoolLessonsAndCertificatesMicroservice;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.SaveStudentRequestForSchoolLessonsAndCertificatesMicroservice;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.SaveStudentResponseForSchoolLessonsAndCertificatesMicroservice;
import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.response.student.RegistrationStudentInDatabaseResponse;
import SchoolStudent.core.validations.student.RegistrationStudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationStudentInDatabaseService {

    @Autowired private SchoolStudentRepository repository;
    @Autowired private RegistrationStudentValidator validator;
    @Autowired private SchoolClassRepository repository2;
    @Autowired
    private SchoolLessonsAndCertificatesMicroservice microservice;

    public RegistrationStudentInDatabaseResponse execute(RegistrationStudentInDatabaseRequest request){
        RegistrationStudentInDatabaseResponse result = validateRequest(request);
        if (!result.hasErrors()) {
            repository.save(buildSchoolStudent(request));
        }
        return result;
    }
    private RegistrationStudentInDatabaseResponse validateRequest(RegistrationStudentInDatabaseRequest request){
        RegistrationStudentInDatabaseResponse response = new RegistrationStudentInDatabaseResponse();
        if (!validator.validate(request).isEmpty()){
           response.setErrors(validator.validate(request));
        }else {
            SaveStudentResponseForSchoolLessonsAndCertificatesMicroservice responseResult  = microservice.execute(buildRequestToSendToMicroservice(request));
            if (responseResult.hasErrors()){
                response.setErrors(responseResult.getErrors());
            }else {
                response.setMessage(responseResult.getSuccessfulMessage());
            }
        }
        return response;
    }

    private SchoolStudent buildSchoolStudent(RegistrationStudentInDatabaseRequest request){
        SchoolStudent schoolChild = new SchoolStudent();
        schoolChild.setFirst_name(request.getFirstName());
        schoolChild.setLast_name(request.getLastName());
        schoolChild.setEmail(request.getEmail());
        schoolChild.setFatherland(request.getFatherland());
        schoolChild.setPassword(request.getPassword());
        schoolChild.setClasses_id(repository2.findBynumberAndcategory(request.getNumber(),request.getCategory()));
        return schoolChild;
    }

    private SaveStudentRequestForSchoolLessonsAndCertificatesMicroservice buildRequestToSendToMicroservice(RegistrationStudentInDatabaseRequest registrationStudentInDatabaseRequest){
        SaveStudentRequestForSchoolLessonsAndCertificatesMicroservice request = new SaveStudentRequestForSchoolLessonsAndCertificatesMicroservice(registrationStudentInDatabaseRequest.getFirstName(),
                registrationStudentInDatabaseRequest.getLastName(),
                registrationStudentInDatabaseRequest.getFatherland(),
                registrationStudentInDatabaseRequest.getEmail(),
                registrationStudentInDatabaseRequest.getNumber(),
                registrationStudentInDatabaseRequest.getCategory());
        return request;
    }
}
