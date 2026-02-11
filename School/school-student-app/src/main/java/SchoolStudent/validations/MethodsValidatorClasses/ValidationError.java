package SchoolStudent.validations.MethodsValidatorClasses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationError {

    private String errorCode;
    private String description;
}
