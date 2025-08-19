package SchoolStudent.core.validations.loginIntoYourPage;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.LoginToYourStudentPageRequest;
import SchoolStudent.core.validations.validatorMethodsFactory.ValidationMethodsFactory;
import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorClassWithMethodsForParametersEmailAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginIntoYourPageValidator {

    @Autowired private ValidationMethodsFactory validationMethodsFactory;

    public List<ValidationErrorDTO> validate(LoginToYourStudentPageRequest request) {
        ValidatorClassWithMethodsForParametersEmailAndPassword validation = validationMethodsFactory.getValidatorsMethodsList().stream()
                .filter(validator -> validator instanceof ValidatorClassWithMethodsForParametersEmailAndPassword)
                .map(validator -> (ValidatorClassWithMethodsForParametersEmailAndPassword) validator)
                .findFirst()
                .orElse(null);
        assert validation != null;
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
