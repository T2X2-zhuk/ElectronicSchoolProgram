package SchoolStudent.core.validations.teacher.Registration;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class RegistrationTeacherValidationFirstNameLastNameFatherlandFields implements RegistrationTeacherValidation {

    @Autowired
    private ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherland validation;

    public List<ValidationErrorDTO> validationErrorDTOSList(RegistrationTeacherRequest request){
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validateFirstName(request).ifPresent(errorDTOS::add);
        validateLastName(request).ifPresent(errorDTOS::add);
        validateFatherland(request).ifPresent(errorDTOS::add);
        return errorDTOS;
    }

    private Optional<ValidationErrorDTO> validateFirstName(RegistrationTeacherRequest request) {
        if (validation.firstNameMustNotBeEmpty(request.getFirst_name()).isPresent()){
            return validation.firstNameMustNotBeEmpty(request.getFirst_name());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateLastName(RegistrationTeacherRequest request) {
        if (validation.lastNameMustNotBeEmpty(request.getLast_name()).isPresent()){
            return validation.lastNameMustNotBeEmpty(request.getLast_name());
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateFatherland(RegistrationTeacherRequest request) {
        if (validation.fatherlandMustNotBeEmpty(request.getFatherland()).isPresent()){
            return validation.fatherlandMustNotBeEmpty(request.getFatherland());
        }
        return Optional.empty();
    }
}
