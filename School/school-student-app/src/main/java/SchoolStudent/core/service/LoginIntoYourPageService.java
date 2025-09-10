package SchoolStudent.core.service;

import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.request.student.LoginToYourStudentPageRequest;
import SchoolStudent.core.response.student.LoginToYourPageForStudentResponse;
import SchoolStudent.core.validations.student.LoginIntoStudentPageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LoginIntoYourPageService {

    @Autowired private SchoolStudentRepository repository;

    @Autowired private LoginIntoStudentPageValidator validator;

    public LoginToYourPageForStudentResponse execute(LoginToYourStudentPageRequest request){
        if (unsuccessful(request).hasErrors()){
            return unsuccessful(request);
        }else {
            return successful(request);
        }
    }
    private LoginToYourPageForStudentResponse successful(LoginToYourStudentPageRequest request){
        Optional<SchoolStudent> schoolStudent = repository.findBypassword(request.getPassword());
        LoginToYourPageForStudentResponse response = new LoginToYourPageForStudentResponse();
        response.setSchoolStudent(schoolStudent.get());
        return response;
    }
    private LoginToYourPageForStudentResponse unsuccessful(LoginToYourStudentPageRequest request){
        if (!validator.validate(request).isEmpty()){
            return new LoginToYourPageForStudentResponse(validator.validate(request));
        }else {
            return new LoginToYourPageForStudentResponse();
        }
    }
}
