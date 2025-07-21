package SchoolStudent.core.validations.FieldsForStudent;

import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FieldPasswordValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Autowired private SchoolStudentRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        if (mustNotBeEmpty(request).isPresent()){
            return mustNotBeEmpty(request);
        }else if (suchPasswordAlreadyExistsValidation(request).isPresent()){
            return suchPasswordAlreadyExistsValidation(request);
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> mustNotBeEmpty(RegistrationStudentInDatabaseRequest request){
        return (isNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> suchPasswordAlreadyExistsValidation(RegistrationStudentInDatabaseRequest request){
        return (repository.findBypassword(request.getPassword()).isPresent())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_9"))
                : Optional.empty();
    }

    private boolean isNullOrBlank(RegistrationStudentInDatabaseRequest request) {
        return request.getPassword() == null || request.getPassword().isBlank();
    }
}
