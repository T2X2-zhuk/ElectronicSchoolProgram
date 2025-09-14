package SchoolStudent.core.validations.student.Registration;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
 class RegistrationStudentValidationPasswordAndEmailFields implements RegistrationStudentValidation {

    @Autowired
    private ValidatorClassWithMethodsForParametersEmailAndPassword validation;

    @Override
    public List<ValidationErrorDTO> validationErrorDTOSList(RegistrationStudentInDatabaseRequest request){
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validateEmail(request).ifPresent(errorDTOS::add);
        validatePassword(request).ifPresent(errorDTOS::add);
        return errorDTOS;
    }

    private Optional<ValidationErrorDTO> validateEmail(RegistrationStudentInDatabaseRequest request) {
        if (validation.mustNotBeEmptyEmail(request.getEmail()).isPresent()){
            return validation.mustNotBeEmptyEmail(request.getEmail());

        }else if (validation.emailYourEmailIsNotCorrectError(request.getEmail()).isPresent()){
            return validation.emailYourEmailIsNotCorrectError(request.getEmail());
            }
        else if (validation.suchEmailAlreadyExistsErrorForStudent(request.getEmail()).isPresent()){
                return validation.suchEmailAlreadyExistsErrorForStudent(request.getEmail());
            }
            return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validatePassword(RegistrationStudentInDatabaseRequest request) {
        if (validation.mustNotBeEmptyPassword(request.getPassword()).isPresent()){
            return validation.mustNotBeEmptyPassword(request.getPassword());
        }else if (validation.suchPasswordAlreadyExistsForStudent(request.getPassword()).isPresent()){
            return validation.suchPasswordAlreadyExistsForStudent(request.getPassword());
        }
        return Optional.empty();
    }

}
