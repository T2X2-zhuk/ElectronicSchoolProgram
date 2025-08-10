package SchoolStudent.core.service;


import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.domain.SchoolClass;
import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.response.TransferOfStudentToANewClassResponse;
import SchoolStudent.core.validations.validatorForTransferOfStudentToANewClass.FieldForTransferOfStudentToANewClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        SchoolStudent schoolStudent = repository.findByemail(request.getEmail()).get();
        response.setMessage(schoolStudent.getFirst_name() + " " + schoolStudent.getLast_name() + " " + schoolStudent.getFatherland() + " - " + "successfully transferred to " + schoolStudent.getClasses_id().getNumber() + " " + schoolStudent.getClasses_id().getCategory() + " class!");
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
