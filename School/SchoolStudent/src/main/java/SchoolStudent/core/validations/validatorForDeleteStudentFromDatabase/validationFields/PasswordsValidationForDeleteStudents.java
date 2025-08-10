package SchoolStudent.core.validations.validatorForDeleteStudentFromDatabase.validationFields;

import SchoolStudent.core.database.SchoolStudentRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.validations.ValidationErrorFactory;
import SchoolStudent.core.validations.validatorForDeleteStudentFromDatabase.RequestFieldsValidationForDeleteStudentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class PasswordsValidationForDeleteStudents extends RequestFieldsValidationForDeleteStudentImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Autowired private SchoolStudentRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(List<String> passwords) {
        List<String> passwordsList = passwords.stream().filter(password -> !suchPasswordNotInDatabase(password)).toList();
        if (passwordsList.isEmpty()){
            return Optional.of(errorFactory.buildError("ERROR_CODE_4")) ;
        }
        return Optional.empty();
    }

    private boolean suchPasswordNotInDatabase(String password){
        return repository.findBypassword(password).isEmpty();
    }
}
