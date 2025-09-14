package SchoolStudent.core.validations.teacher.Registration;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
 class RegistrationTeacherValidationPasswordAndEmailFields implements RegistrationTeacherValidation {

    @Autowired
    private ValidatorClassWithMethodsForParametersEmailAndPassword validation;

    @Override
    public List<ValidationErrorDTO> validationErrorDTOSList(RegistrationTeacherRequest request){
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validateEmail(request).ifPresent(errorDTOS::add);
        validatePassword(request).ifPresent(errorDTOS::add);
        return errorDTOS;
    }

    private Optional<ValidationErrorDTO> validateEmail(RegistrationTeacherRequest request) {
        if (validation.mustNotBeEmptyEmail(request.getEmail()).isPresent()){
            return validation.mustNotBeEmptyEmail(request.getEmail());

        }else if (validation.emailYourEmailIsNotCorrectError(request.getEmail()).isPresent()){
            return validation.emailYourEmailIsNotCorrectError(request.getEmail());
            }
        else if (validation.suchEmailAlreadyExistsErrorForTeacher(request.getEmail()).isPresent()){
                return validation.suchEmailAlreadyExistsErrorForTeacher(request.getEmail());
            }
            return Optional.empty();
    }

    private Optional<ValidationErrorDTO> validatePassword(RegistrationTeacherRequest request) {
        if (validation.mustNotBeEmptyPassword(request.getPassword()).isPresent()){
            return validation.mustNotBeEmptyPassword(request.getPassword());
        }else if (validation.suchPasswordAlreadyExistsForTeacher(request.getPassword()).isPresent()){
            return validation.suchPasswordAlreadyExistsForTeacher(request.getPassword());
        }
        return Optional.empty();
    }

}
