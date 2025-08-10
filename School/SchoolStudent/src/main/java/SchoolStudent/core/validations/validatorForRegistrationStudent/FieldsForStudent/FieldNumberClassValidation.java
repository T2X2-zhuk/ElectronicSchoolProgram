package SchoolStudent.core.validations.validatorForRegistrationStudent.FieldsForStudent;

import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.validatorForRegistrationStudent.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FieldNumberClassValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Autowired private SchoolClassRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        if (mustNotBeEmpty(request).isPresent()){
            return mustNotBeEmpty(request);
        }if (notSuchSchoolClass(request).isPresent()){
            return notSuchSchoolClass(request);
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> mustNotBeEmpty(RegistrationStudentInDatabaseRequest request){
        return (request.getNumber() == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }


    private Optional<ValidationErrorDTO> notSuchSchoolClass(RegistrationStudentInDatabaseRequest request){
        return (repository.findAllNumberWhichEqualsThisParameter(request.getNumber()).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }
}
