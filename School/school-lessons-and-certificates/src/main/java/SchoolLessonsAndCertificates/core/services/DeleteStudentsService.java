package SchoolLessonsAndCertificates.core.services;

import SchoolLessonsAndCertificates.core.database.StudentRepository;
import SchoolLessonsAndCertificates.core.request.DeleteStudentsRequest;
import SchoolLessonsAndCertificates.core.response.DeleteStudentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteStudentsService {

    @Autowired private StudentRepository repository;

    public DeleteStudentsResponse execute(DeleteStudentsRequest request){
        return process(request);
    }
    private DeleteStudentsResponse process(DeleteStudentsRequest request){
        DeleteStudentsResponse response = new DeleteStudentsResponse();
        request.getEmails()
                .forEach(email -> repository.deleteByEmail(email));
        response.setMessage("Student/Students matching the specified passwords have been successfully deleted.");
        return response;
    }
}
