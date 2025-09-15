package SchoolStudent.core.validations.teacher.Registration;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.core.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherlandSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class RegistrationTeacherValidationFirstNameLastNameFatherlandSubjectFields implements RegistrationTeacherValidation {

    @Autowired
    private ValidatorClassWithMethodsForParametersFirstNameAndLastNameAndFatherlandSubject validation;

    public List<ValidationErrorDTO> validationErrorDTOSList(RegistrationTeacherRequest request){
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validation.firstNameMustNotBeEmpty(request.getFirst_name()).ifPresent(errorDTOS::add);
        validation.lastNameMustNotBeEmpty(request.getLast_name()).ifPresent(errorDTOS::add);
        validation.fatherlandMustNotBeEmpty(request.getFatherland()).ifPresent(errorDTOS::add);
        validation.subjectMustNotBeEmpty(request.getSubject()).ifPresent(errorDTOS::add);
        return errorDTOS;
    }
}
