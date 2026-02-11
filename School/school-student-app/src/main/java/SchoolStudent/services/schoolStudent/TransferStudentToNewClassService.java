package SchoolStudent.services.schoolStudent;


import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.jpa.repositories.SchoolStudentRepository;
import SchoolStudent.request.student.TransferStudentToNewClassRequest;
import SchoolStudent.response.student.TransferStudentToNewClassResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.student.TransferStudentToNewClassValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferStudentToNewClassService {

    private final TransferStudentToNewClassValidator validator;
    private final SchoolStudentRepository repository;
    private final SchoolClassRepository repository2;

    @Transactional
    public TransferStudentToNewClassResponse execute(TransferStudentToNewClassRequest request){
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return TransferStudentToNewClassResponse.builder().errors(errors).build();
        }
        SchoolClass schoolClass = repository2.findByNumberAndCategory(request.getSchoolClassDTO().getNumber(), request.getSchoolClassDTO().getCategory()).get();
        repository.updateStudentClass(request.getEmail(),schoolClass);
        return TransferStudentToNewClassResponse.builder().message("This student successfully transferred to new class").build();
    }

}
