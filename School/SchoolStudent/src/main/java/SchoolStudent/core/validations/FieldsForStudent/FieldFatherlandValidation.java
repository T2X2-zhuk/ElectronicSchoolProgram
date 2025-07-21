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
public class FieldFatherlandValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;
    @Autowired private SchoolStudentRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        if (validateMustNotBeEmpty(request).isPresent()){
            return validateMustNotBeEmpty(request);
        }
        if (validateFatherlandShouldNotBeRepeated(request).isPresent()){
            return validateFatherlandShouldNotBeRepeated(request);
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateMustNotBeEmpty(RegistrationStudentInDatabaseRequest request){
        return (isNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateFatherlandShouldNotBeRepeated(RegistrationStudentInDatabaseRequest request){
        return (repository.findByfatherland(request.getFatherland()).isPresent())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_4"))
                : Optional.empty();
    }

    private boolean isNullOrBlank(RegistrationStudentInDatabaseRequest request) {
        return request.getFatherland() == null || request.getFatherland().isBlank();
    }
}
