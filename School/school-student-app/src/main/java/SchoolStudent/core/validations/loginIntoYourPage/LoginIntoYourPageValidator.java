package SchoolStudent.core.validations.loginIntoYourPage;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.LoginToYourStudentPageRequest;
import SchoolStudent.core.validations.validatorMethodsDirectory.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginIntoYourPageValidator {

    @Autowired private ValidatorClassWithMethodsForParametersEmailAndPassword validation;

    public List<ValidationErrorDTO> validate(LoginToYourStudentPageRequest request) {
        List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
        if (validation.mustNotBeEmptyPassword(request.getPassword()).isPresent()){
            errorDTOS.add(validation.mustNotBeEmptyPassword(request.getPassword()).get());
            return errorDTOS;
        }else if (validation.suchPasswordIsNotExistsValidation(request.getPassword()).isPresent()){
            errorDTOS.add(validation.suchPasswordIsNotExistsValidation(request.getPassword()).get());
            return errorDTOS;
        }
        return errorDTOS;
    }
}
