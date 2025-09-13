package SchoolLessonsAndCertificates.core.services;

import SchoolLessonsAndCertificates.core.database.StudentRepository;
import SchoolLessonsAndCertificates.core.domain.Student;
import SchoolLessonsAndCertificates.core.request.SaveStudentRequest;
import SchoolLessonsAndCertificates.core.response.SaveStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaveStudentService{

    @Autowired
    private StudentRepository repository;

    public SaveStudentResponse execute(SaveStudentRequest request){
        SaveStudentResponse response = new SaveStudentResponse();
        response.setSuccessfulMessage("You successfully registered in the database");
        repository.save(buildStudentForSaveHis(request));
        return response;
    }
    private Student buildStudentForSaveHis(SaveStudentRequest request){
        Student student = new Student();
        student.setFirst_name(request.getFirstName());
        student.setLast_name(request.getLastName());
        student.setFatherland(request.getFatherland());
        student.setEmail(request.getEmail());
        student.setNumber(request.getNumberClass());
        student.setCategory(request.getCategory());
        return student;
    }
}
