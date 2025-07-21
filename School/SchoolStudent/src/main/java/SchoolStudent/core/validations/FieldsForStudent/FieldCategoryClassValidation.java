package SchoolStudent.core.validations.FieldsForStudent;

import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.RequestFieldsValidationImpl;
import SchoolStudent.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FieldCategoryClassValidation extends RequestFieldsValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;
    @Autowired private SchoolClassRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(RegistrationStudentInDatabaseRequest request) {

        if (fieldCategoryClassMustNotBeEmpty(request).isPresent()){
            return fieldCategoryClassMustNotBeEmpty(request);
        } else if (fieldCategoryClassMustContainOneCapitalEnglishLetter(request).isPresent()){
            return fieldCategoryClassMustContainOneCapitalEnglishLetter(request);
        } else if (suchCategoryClassIsNotInDatabase(request).isPresent()){
            return suchCategoryClassIsNotInDatabase(request);
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> fieldCategoryClassMustNotBeEmpty(RegistrationStudentInDatabaseRequest request){
        return (isNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }
    private Optional<ValidationErrorDTO> fieldCategoryClassMustContainOneCapitalEnglishLetter(RegistrationStudentInDatabaseRequest request){
        String regex = "^[A-Z]$";
        return (!request.getCategory().matches(regex))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> suchCategoryClassIsNotInDatabase(RegistrationStudentInDatabaseRequest request){
        return (repository.findBycategory(request.getCategory()).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }

    private boolean isNullOrBlank(RegistrationStudentInDatabaseRequest request) {
        return request.getCategory() == null || request.getCategory().isEmpty();
    }
}
