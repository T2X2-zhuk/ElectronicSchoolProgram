package SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase;

import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
 public class ValidatorClassWithMethodsForParametersEmailAndPassword implements ValidatorFieldMethods{


    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired private SchoolStudentRepository repository;
    @Autowired private SchoolClassRepository repository2;

    public Optional<ValidationErrorDTO> mustNotBeEmptyEmail(String email){
        return (isNullOrBlankOrEmpty(email))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> suchEmailAlreadyExistsError(String email){
        return (repository.findByemail(email).isPresent())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_6"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> emailYourEmailIsNotCorrectError(String email){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        java.util.regex.Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return (!matcher.matches())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> suchPasswordIsNotExistsValidation(String password){
        return (repository.findBypassword(password).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_18"))
                : Optional.empty();
    }


    public Optional<ValidationErrorDTO> mustNotBeEmptyPassword(String password){
        return (isNullOrBlankOrEmpty(password))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> suchPasswordAlreadyExistsValidation(String password){
        return (repository.findBypassword(password).isPresent())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_9"))
                : Optional.empty();
    }

    public Optional<ValidationErrorDTO> notEnteredASingleCorrectPassword(List<String> passwords) {
        List<String> passwordsList = passwords.stream().filter(password -> repository.findBypassword(password).isPresent()).toList();
        if (passwordsList.isEmpty()){
            return Optional.of(errorFactory.buildError("ERROR_CODE_4"));
        }
        return Optional.empty();
    }

    public Optional<ValidationErrorDTO> whetherThereIsSuchAStudentInTheDatabaseOrNot(String email) {
        if (repository.findByemail(email).isEmpty()){
            return Optional.of(errorFactory.buildError("ERROR_CODE_17"));
        }else {
            return Optional.empty();
        }
    }
    private boolean isNullOrBlankOrEmpty(String parameter) {
        return parameter == null || parameter.isBlank() || parameter.isEmpty();
    }

}
