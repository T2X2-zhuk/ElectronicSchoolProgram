package SchoolStudent.core.service.services;

import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForSaveStudent;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.SaveStudentForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.SaveStudentForSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.response.student.RegistrationStudentInDatabaseResponse;
import SchoolStudent.core.service.interfeicesForServices.RegistrationStudentInDatabaseService;
import SchoolStudent.core.validations.student.RegistrationStudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class RegistrationStudentInDatabaseServiceImpl implements RegistrationStudentInDatabaseService {

    @Autowired private SchoolStudentRepository repository;
    @Autowired private RegistrationStudentValidator validator;
    @Autowired private SchoolClassRepository repository2;
    @Autowired
    private SchoolLessonsAndCertificatesMicroserviceForSaveStudent microservice;

    public RegistrationStudentInDatabaseResponse execute(RegistrationStudentInDatabaseRequest request){
        return validateRequest(request);
    }
    private RegistrationStudentInDatabaseResponse validateRequest(RegistrationStudentInDatabaseRequest request){
        RegistrationStudentInDatabaseResponse response = new RegistrationStudentInDatabaseResponse();
        if (!validator.validate(request).isEmpty()){
           response.setErrors(validator.validate(request));
        }else {
            SaveStudentForSchoolLessonsAndCertificatesMicroserviceResponse responseResult  = microservice.execute(buildRequestToSendToMicroservice(request));
            repository.save(buildSchoolStudent(request));
            response.setMessage(responseResult.getSuccessfulMessage());
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

    private SaveStudentForSchoolLessonsAndCertificatesMicroserviceRequest buildRequestToSendToMicroservice(RegistrationStudentInDatabaseRequest registrationStudentInDatabaseRequest){
        return new SaveStudentForSchoolLessonsAndCertificatesMicroserviceRequest(registrationStudentInDatabaseRequest.getFirstName(),
                registrationStudentInDatabaseRequest.getLastName(),
                registrationStudentInDatabaseRequest.getFatherland(),
                registrationStudentInDatabaseRequest.getEmail(),
                registrationStudentInDatabaseRequest.getNumber(),
                registrationStudentInDatabaseRequest.getCategory());
    }
}
