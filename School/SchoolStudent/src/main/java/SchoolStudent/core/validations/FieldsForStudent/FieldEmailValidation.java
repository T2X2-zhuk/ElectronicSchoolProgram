package SchoolStudent.core.validations.FieldsForStudent;

import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.ValidationErrorFactory;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
@Component
public class FieldEmailValidation extends RequestFieldsValidationImpl {

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Autowired private SchoolStudentRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {
        if (mustNotBeEmpty(request).isPresent()){
            return mustNotBeEmpty(request);
        }else if (emailYourEmailIsNotCorrectValidation(request).isPresent()){
            return emailYourEmailIsNotCorrectValidation(request);
        }else if (suchEmailAlreadyExistsValidation(request).isPresent()){
            return suchEmailAlreadyExistsValidation(request);
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> mustNotBeEmpty(RegistrationStudentInDatabaseRequest request){
        return (isNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> suchEmailAlreadyExistsValidation(RegistrationStudentInDatabaseRequest request){
        return (repository.findByemail(request.getEmail()).isPresent())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_6"))
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> emailYourEmailIsNotCorrectValidation(RegistrationStudentInDatabaseRequest request){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        java.util.regex.Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(request.getEmail());
        return (!matcher.matches())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

    private boolean isNullOrBlank(RegistrationStudentInDatabaseRequest request) {
        return request.getEmail() == null || request.getEmail().isBlank();
    }
}
