package SchoolStudent.core.validations.validatorForTransferOfStudentToANewClass.FieldsForStudent;

import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.ValidationErrorFactory;
import SchoolStudent.core.validations.validatorForTransferOfStudentToANewClass.TransferOfStudentToANewClassRequestFieldsValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 class FieldNewNumberClassForTransferValidation extends TransferOfStudentToANewClassRequestFieldsValidationImpl
{

    @Autowired private ValidationErrorFactory errorFactory;

    @Autowired private SchoolClassRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        if (mustNotBeEmpty(request).isPresent()){
            return mustNotBeEmpty(request);
        }if (notSuchSchoolClass(request).isPresent()){
            return notSuchSchoolClass(request);
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> mustNotBeEmpty(TransferOfStudentToANewClassRequest request){
        return (request.getNewClassNumber() == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }


    private Optional<ValidationErrorDTO> notSuchSchoolClass(TransferOfStudentToANewClassRequest request){
        return (repository.findAllNumberWhichEqualsThisParameter(request.getNewClassNumber()).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }
}
