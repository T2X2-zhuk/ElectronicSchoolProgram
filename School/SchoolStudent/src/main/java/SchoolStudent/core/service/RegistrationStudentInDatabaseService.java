package SchoolStudent.core.service;

import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.response.RegistrationStudentInDatabaseResponse;
import SchoolStudent.core.validations.validatorForRegistrationStudent.FieldForRegistrationStudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationStudentInDatabaseService {

    @Autowired private SchoolStudentRepository repository;
    @Autowired private FieldForRegistrationStudentValidator validator;
    @Autowired private SchoolClassRepository repository2;

    public RegistrationStudentInDatabaseResponse execute(RegistrationStudentInDatabaseRequest request){
        if (buildResponseWithErrors(request).hasErrors()){
            return buildResponseWithErrors(request);
        }else {
            return saveSchoolChild(request);
        }
    }

    private RegistrationStudentInDatabaseResponse saveSchoolChild(RegistrationStudentInDatabaseRequest request){
        SchoolStudent schoolChild = new SchoolStudent();
        schoolChild.setFirst_name(request.getFirstName());
        schoolChild.setLast_name(request.getLastName());
        schoolChild.setEmail(request.getEmail());
        schoolChild.setFatherland(request.getFatherland());
        schoolChild.setPassword(request.getPassword());
        schoolChild.setClasses_id(repository2.findBynumberAndcategory(request.getNumber(),request.getCategory()));
        repository.save(schoolChild);
        RegistrationStudentInDatabaseResponse response = new RegistrationStudentInDatabaseResponse();
        response.setMessage(request.getFirstName() + " " + request.getLastName() + " " + request.getFatherland() + " - " + "successfully registered in the database");
        return response;
    }
    private RegistrationStudentInDatabaseResponse buildResponseWithErrors(RegistrationStudentInDatabaseRequest request){
        if (!validator.validate(request).isEmpty()){
            return new RegistrationStudentInDatabaseResponse(validator.validate(request));
        }else {
            return new RegistrationStudentInDatabaseResponse();
        }
    }
}
