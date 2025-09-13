package SchoolLessonsAndCertificates.core.services;

import SchoolLessonsAndCertificates.core.database.StudentRepository;
import SchoolLessonsAndCertificates.core.request.TransferOfStudentToANewClassRequest;
import SchoolLessonsAndCertificates.core.response.TransferStudentToANewClassResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class TransferStudentToNewClassService {

    @Autowired private StudentRepository repository;

    public TransferStudentToANewClassResponse execute(TransferOfStudentToANewClassRequest request){
        TransferStudentToANewClassResponse response = new TransferStudentToANewClassResponse();
        repository.forUpdateStudentData(request.getNumber(),request.getCategory(),request.getEmail());
        response.setMessage("Student with email " + request.getEmail() + " successfully transferred to " + request.getNumber() + " " + request.getCategory() + " class!");
        return response;
    }
}
