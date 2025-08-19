package SchoolStudent.core.validations.validatorMethodsFactory;

import SchoolStudent.core.validations.validatorMethodsFactory.validatorMethodsCase.ValidatorFieldMethods;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class ValidationMethodsFactory {

    @Autowired private List<ValidatorFieldMethods> validatorsMethodsList;
}
