package SchoolStudent.core.service.services;


import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.TransferStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForTransferStudent;
import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.domain.SchoolClass;
import SchoolStudent.core.request.student.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.response.student.TransferOfStudentToANewClassResponse;
import SchoolStudent.core.service.interfeicesForServices.TransferOfStudentToANewClassService;
import SchoolStudent.core.validations.student.TransferStudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class TransferOfStudentToANewClassServiceImpl implements TransferOfStudentToANewClassService {

    @Autowired private TransferStudentValidator validator;
    @Autowired private SchoolStudentRepository repository;
    @Autowired private SchoolClassRepository repository2;
    @Autowired private SchoolLessonsAndCertificatesMicroserviceForTransferStudent microservice;

    @Override
    public TransferOfStudentToANewClassResponse execute(TransferOfStudentToANewClassRequest request){
        return validateRequest(request);
    }

    private TransferOfStudentToANewClassResponse validateRequest(TransferOfStudentToANewClassRequest request){
        TransferOfStudentToANewClassResponse response = new TransferOfStudentToANewClassResponse();
        if (!validator.validate(request).isEmpty()){
            response.setErrors(validator.validate(request));
        }else {
            TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest microserviceRequest = new TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest(request.getNewClassNumber(), request.getCategory(), request.getEmail());
            TransferStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceResponse microserviceResponse = microservice.execute(microserviceRequest);
            response.setMessage(microserviceResponse.getMessage());
            successful(request);
        }
        return response;
    }
    private void successful(TransferOfStudentToANewClassRequest request){
        SchoolClass schoolClass = repository2.findBynumberAndcategory(request.getNewClassNumber(), request.getCategory());
        repository.forUpdateStudentData(request.getEmail(),schoolClass);
    }

}
