package SchoolLessonsAndCertificates.core.validators.saveStudent;

import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.request.SaveStudentRequest;
import SchoolLessonsAndCertificates.core.validators.MethodsValidatorClasses.ValidatorClassWithMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IsStudentInDatabaseOrNot implements SaveStudentValidation {
   @Autowired
   private ValidatorClassWithMethods validatorClassWithMethods;
    @Override
    public List<ValidationErrorDTO> validationErrorDTOSList(SaveStudentRequest request) {
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        validatorClassWithMethods.isStudentInDatabaseError(request.getFirstName(),request.getLastName(),request.getFatherland(),request.getEmail()).ifPresent(errorDTOS::add);
        return errorDTOS;
    }
}
