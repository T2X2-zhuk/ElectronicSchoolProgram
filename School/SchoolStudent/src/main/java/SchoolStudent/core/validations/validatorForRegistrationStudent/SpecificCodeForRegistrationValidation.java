package SchoolStudent.core.validations.validatorForRegistrationStudent;

import SchoolStudent.core.database.CodeForRegistrationRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
 class SpecificCodeForRegistrationValidation extends RequestFieldsValidationImpl{

    @Autowired private ValidationErrorFactory errorFactory;
    @Autowired private CodeForRegistrationRepository repository;
    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        if (mustNotBeEmpty(request).isPresent()){
            return mustNotBeEmpty(request);
        }  if (theWrongSpecificCodeForRegistration(request).isPresent()){
            return theWrongSpecificCodeForRegistration(request);
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> mustNotBeEmpty(RegistrationStudentInDatabaseRequest request){
       return (isNullOrBlank(request))
       ? Optional.of(errorFactory.buildError("ERROR_CODE_15"))
       : Optional.empty();
    }

    private Optional<ValidationErrorDTO> theWrongSpecificCodeForRegistration(RegistrationStudentInDatabaseRequest request){
        return (repository.findByspecificCodeForRegistrationForStudent(request.getSpecificCodeForRegistration()).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_16"))
                : Optional.empty();
    }

    private boolean isNullOrBlank(RegistrationStudentInDatabaseRequest request) {
        return request.getSpecificCodeForRegistration() == null || request.getSpecificCodeForRegistration().isEmpty();
    }
}
