package SchoolStudent.core.validations.validatorForTransferOfStudentToANewClass.FieldsForStudent;

import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.ValidationErrorFactory;
import SchoolStudent.core.validations.validatorForTransferOfStudentToANewClass.TransferOfStudentToANewClassRequestFieldsValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
class WhetherThereIsSuchAStudentInTheDatabaseOrNot extends TransferOfStudentToANewClassRequestFieldsValidationImpl {

    @Autowired private SchoolStudentRepository repository;

    @Autowired private ValidationErrorFactory factory;
    @Override
    public Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        if (repository.findByemail(request.getEmail()).isEmpty()){
            return Optional.of(factory.buildError("ERROR_CODE_17"));
        }else {
            return Optional.empty();
        }
    }


}
