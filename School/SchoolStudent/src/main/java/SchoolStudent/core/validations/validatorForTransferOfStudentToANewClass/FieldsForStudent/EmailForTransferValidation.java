package SchoolStudent.core.validations.validatorForTransferOfStudentToANewClass.FieldsForStudent;

import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.ValidationErrorFactory;
import SchoolStudent.core.validations.validatorForTransferOfStudentToANewClass.TransferOfStudentToANewClassRequestFieldsValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
class EmailForTransferValidation extends TransferOfStudentToANewClassRequestFieldsValidationImpl {

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Autowired private SchoolStudentRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        if (mustNotBeEmpty(request).isPresent()){
            return mustNotBeEmpty(request);
        }else if (emailYourEmailIsNotCorrectValidation(request).isPresent()){
            return emailYourEmailIsNotCorrectValidation(request);
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> mustNotBeEmpty(TransferOfStudentToANewClassRequest request){
        return (isNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> emailYourEmailIsNotCorrectValidation(TransferOfStudentToANewClassRequest request){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        java.util.regex.Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(request.getEmail());
        return (!matcher.matches())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

    private boolean isNullOrBlank(TransferOfStudentToANewClassRequest request) {
        return request.getEmail() == null || request.getEmail().isEmpty();
    }
}
