package SchoolStudent.core.service;


import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.response.DeleteStudentFromDatabaseResponse;
import SchoolStudent.core.validations.forDeleteStudentFromDatabase.ForDeleteStudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteStudentFromDatabaseService {

    @Autowired private SchoolStudentRepository repository;
    @Autowired private ForDeleteStudentValidator validator;

    public DeleteStudentFromDatabaseResponse  execute(DeleteStudentFromDatabaseRequest request){
        if (unsuccessfulDelete(request).hasErrors()){
            return unsuccessfulDelete(request);
        } else {
            return successfulDelete(request);
        }
    }

    private DeleteStudentFromDatabaseResponse successfulDelete(DeleteStudentFromDatabaseRequest request){
        List<String> passwordFilter = request.getPasswords().stream().filter(password -> repository.findBypassword(password).isPresent()).toList();
        passwordFilter.forEach(password -> repository.deleteByPassword(password));
        DeleteStudentFromDatabaseResponse response = new DeleteStudentFromDatabaseResponse();
        response.setMessage("Student/Students matching the specified passwords have been successfully deleted.");
        return response;
    }

    private DeleteStudentFromDatabaseResponse unsuccessfulDelete(DeleteStudentFromDatabaseRequest request){
       return new DeleteStudentFromDatabaseResponse(validator.validate(request));
    }
}
