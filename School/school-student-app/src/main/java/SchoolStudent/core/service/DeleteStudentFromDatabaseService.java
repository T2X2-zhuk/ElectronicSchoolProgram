package SchoolStudent.core.service;


import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.request.student.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.response.student.DeleteStudentFromDatabaseResponse;
import SchoolStudent.core.validations.student.DeleteStudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeleteStudentFromDatabaseService {

    @Autowired private SchoolStudentRepository repository;
    @Autowired private DeleteStudentValidator validator;

    public DeleteStudentFromDatabaseResponse  execute(DeleteStudentFromDatabaseRequest request){
        if (unsuccessfulDelete(request).hasErrors()){
            return unsuccessfulDelete(request);
        } else {
            return successfulDelete(request);
        }
    }

    private DeleteStudentFromDatabaseResponse successfulDelete(DeleteStudentFromDatabaseRequest request){
        List<String> passwordFilter = Optional.ofNullable(request.getPasswords())
                .orElse(Collections.emptyList()) // если null → пустой список
                .stream()
                .filter(password -> repository.findBypassword(password).isPresent())
                .toList();
        passwordFilter.forEach(password -> repository.deleteByPassword(password));
        DeleteStudentFromDatabaseResponse response = new DeleteStudentFromDatabaseResponse();
        response.setMessage("Student/Students matching the specified passwords have been successfully deleted.");
        return response;
    }

    private DeleteStudentFromDatabaseResponse unsuccessfulDelete(DeleteStudentFromDatabaseRequest request){
       return new DeleteStudentFromDatabaseResponse(validator.validate(request));
    }
}
