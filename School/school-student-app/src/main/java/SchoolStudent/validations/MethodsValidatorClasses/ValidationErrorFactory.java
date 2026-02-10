package SchoolStudent.validations.MethodsValidatorClasses;

import SchoolStudent.util.ErrorCodeUtil;
import SchoolStudent.util.Placeholder;
import SchoolStudent.util.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidationErrorFactory {

    private final ErrorCodeUtil errorCodeUtil;

    public ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return ValidationError.builder().errorCode(errorCode).description(errorDescription).build();
    }

    public ValidationError buildError(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode, placeholders);
        return ValidationError.builder().errorCode(errorCode).description(errorDescription).build();
    }

}
