package SchoolStudent.core.service;

import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.domain.SchoolClass;
import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.response.GetAllStudentsBySchoolClassResponse;
import SchoolStudent.core.validations.forGetAllStudent.GetAllStudentsBySchoolClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GetAllStudentsBySchoolClassService {

    @Autowired private SchoolStudentRepository repository;
    @Autowired private SchoolClassRepository classRepository;
    @Autowired private GetAllStudentsBySchoolClassValidator validator;

    public GetAllStudentsBySchoolClassResponse execute(GetAllStudentsBySchoolClassRequest request){
        if (unsuccessful(request).hasErrors()){
            return unsuccessful(request);
        }else {
            return successful(request);
        }
    }
    private GetAllStudentsBySchoolClassResponse successful(GetAllStudentsBySchoolClassRequest request){
        SchoolClass schoolClass = classRepository.findBynumberAndcategory(request.getNumber(),request.getCategory());
        List<SchoolStudent> schoolStudents = repository.findAllSchoolStudentByTheGivenClassParameter(schoolClass.getNumber(), schoolClass.getCategory());
        GetAllStudentsBySchoolClassResponse response = new GetAllStudentsBySchoolClassResponse();
        response.setSchoolStudentList(schoolStudents);
        return response;
    }
    private GetAllStudentsBySchoolClassResponse unsuccessful(GetAllStudentsBySchoolClassRequest request){
        if (!validator.validate(request).isEmpty()){
            return new GetAllStudentsBySchoolClassResponse(validator.validate(request));
        }else {
            return new GetAllStudentsBySchoolClassResponse();
        }
    }
}
