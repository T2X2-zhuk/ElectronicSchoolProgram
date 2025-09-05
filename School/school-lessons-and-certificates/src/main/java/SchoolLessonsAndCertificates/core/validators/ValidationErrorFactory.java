package SchoolLessonsAndCertificates.core.validators;

import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import SchoolLessonsAndCertificates.core.util.ErrorCodeUtil;
import SchoolLessonsAndCertificates.core.util.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationErrorFactory {

    @Autowired private ErrorCodeUtil errorCodeUtil;

    public ValidationErrorDTO buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationErrorDTO(errorCode, errorDescription);
    }

    public ValidationErrorDTO buildError(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode, placeholders);
        return new ValidationErrorDTO(errorCode, errorDescription);
    }

}
