package SchoolStudent.core.service;


import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.domain.SchoolClass;
import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.response.TransferOfStudentToANewClassResponse;
import SchoolStudent.core.validations.forTransferOfStudentToANewClass.FieldForTransferOfStudentToANewClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TransferOfStudentToANewClassService {

    @Autowired private FieldForTransferOfStudentToANewClassValidator validator;
    @Autowired private SchoolStudentRepository repository;
    @Autowired private SchoolClassRepository repository2;

    public TransferOfStudentToANewClassResponse execute(TransferOfStudentToANewClassRequest request){
        if (unsuccessful(request).hasErrors()){
            return unsuccessful(request);
        }else {
            return successful(request);
        }
    }

    private TransferOfStudentToANewClassResponse successful(TransferOfStudentToANewClassRequest request){
        SchoolClass schoolClass = repository2.findBynumberAndcategory(request.getNewClassNumber(), request.getCategory());
        repository.forUpdateStudentData(request.getEmail(),schoolClass);
        TransferOfStudentToANewClassResponse response = new TransferOfStudentToANewClassResponse();
        Optional<SchoolStudent> schoolStudent = repository.findByemail(request.getEmail());
        response.setMessage(schoolStudent.get().getFirst_name() + " " + schoolStudent.get().getLast_name() + " " + schoolStudent.get().getFatherland() + " - " + "successfully transferred to " + schoolClass.getNumber() + " " + schoolClass.getCategory() + " class!");
        return response;
    }
    private TransferOfStudentToANewClassResponse unsuccessful(TransferOfStudentToANewClassRequest request){
        if (!validator.validate(request).isEmpty()){
            return new TransferOfStudentToANewClassResponse(validator.validate(request));
        }else {
            return new TransferOfStudentToANewClassResponse();
        }
    }
}
