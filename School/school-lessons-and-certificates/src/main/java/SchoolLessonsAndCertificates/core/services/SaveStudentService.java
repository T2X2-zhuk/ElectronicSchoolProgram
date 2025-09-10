package SchoolLessonsAndCertificates.core.services;

import SchoolLessonsAndCertificates.core.database.StudentRepository;
import SchoolLessonsAndCertificates.core.domain.Student;
import SchoolLessonsAndCertificates.core.request.SaveStudentRequest;
import SchoolLessonsAndCertificates.core.response.SaveStudentResponse;
import SchoolLessonsAndCertificates.core.validators.SaveStudentInDatabaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaveStudentService{


    @Autowired
    private StudentRepository repository;
    @Autowired private SaveStudentInDatabaseValidator validator;

    public SaveStudentResponse execute(SaveStudentRequest request){
        SaveStudentResponse result = validateRequestAndReturnResult(request);
        if (!result.hasErrors()) {
            repository.save(buildStudentForSaveHis(request));
        }
        return result;
    }
    private SaveStudentResponse validateRequestAndReturnResult(SaveStudentRequest request){
        SaveStudentResponse response = new SaveStudentResponse();
        if (!validator.validate(request).isEmpty()){
            response.setErrors(validator.validate(request));
        }else {
            response.setSuccessfulMessage("You successfully registered in the database");
        }
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
