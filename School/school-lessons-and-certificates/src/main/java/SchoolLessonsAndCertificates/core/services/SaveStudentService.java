package SchoolLessonsAndCertificates.core.services;

import SchoolLessonsAndCertificates.core.database.StudentRepository;
import SchoolLessonsAndCertificates.core.domain.Student;
import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.request.SaveStudentRequest;
import SchoolLessonsAndCertificates.core.response.SaveStudentResponse;
import SchoolLessonsAndCertificates.core.validators.SaveStudentInDatabaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaveStudentService {


    @Autowired
    private StudentRepository repository;
    @Autowired private SaveStudentInDatabaseValidator validator;

    public SaveStudentResponse execute(SaveStudentRequest request){
        if (isErrorsOrNot(request).hasErrors()){
            return isErrorsOrNot(request);
        }else {
            successful(request);
            SaveStudentResponse response = new SaveStudentResponse();
            response.setSuccessfulOrUnsuccessfulRegister(true);
            return response;
        }
    }

    private void successful(SaveStudentRequest request){
        Student student = new Student();
        student.setFirst_name(request.getFirstName());
        student.setLast_name(request.getLastName());
        student.setFatherland(request.getFatherland());
        repository.save(student);
    }
    private SaveStudentResponse isErrorsOrNot(SaveStudentRequest request){
        SaveStudentResponse response = new SaveStudentResponse();
        List<ValidationErrorDTO> validationErrorDTOS = validator.validate(request);
        if (!validationErrorDTOS.isEmpty()){
            response.setSuccessfulOrUnsuccessfulRegister(false);
            response.setErrors(validationErrorDTOS);
        }
        return response;
    }
}
