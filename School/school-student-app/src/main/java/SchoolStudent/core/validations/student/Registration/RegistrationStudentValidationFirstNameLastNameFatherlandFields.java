package SchoolStudent.core.validations.student.Registration;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class RegistrationStudentValidationFirstNameLastNameFatherlandFields implements RegistrationStudentValidation{

    @Autowired
    private ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland validation;

    public List<ValidationErrorDTO> validationErrorDTOSList(RegistrationStudentInDatabaseRequest request){
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validateFirstName(request).ifPresent(errorDTOS::add);
        validateLastName(request).ifPresent(errorDTOS::add);
        validateFatherland(request).ifPresent(errorDTOS::add);
        return errorDTOS;
    }

    private Optional<ValidationErrorDTO> validateFirstName(RegistrationStudentInDatabaseRequest request) {
        if (validation.firstNameMustNotBeEmpty(request.getFirstName()).isPresent()){
            return validation.firstNameMustNotBeEmpty(request.getFirstName());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateLastName(RegistrationStudentInDatabaseRequest request) {
        if (validation.lastNameMustNotBeEmpty(request.getLastName()).isPresent()){
            return validation.lastNameMustNotBeEmpty(request.getLastName());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateFatherland(RegistrationStudentInDatabaseRequest request) {
        if (validation.fatherlandMustNotBeEmpty(request.getFatherland()).isPresent()){
            return validation.fatherlandMustNotBeEmpty(request.getFatherland());
        }
        return Optional.empty();
    }
}
