package SchoolStudent.core.validations.validatorForDeleteStudentFromDatabase;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public
class ForDeleteStudentValidator {

    @Autowired
    private List<ForDeleteStudentValidation> forDeleteStudentValidations;

    public List<ValidationErrorDTO> validate(List<String> passwords) {
        List<ValidationErrorDTO> singleErrors = collectSingleStudentErrors(passwords);
        List<ValidationErrorDTO> listErrors = collectListStudentErrors(passwords);
        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationErrorDTO> collectSingleStudentErrors(List<String> passwords) {
        return forDeleteStudentValidations.stream()
                .map(validation -> validation.validate(passwords))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectListStudentErrors(List<String> passwords) {
        return forDeleteStudentValidations.stream()
                .map(validation -> validation.validateList(passwords))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> concatenateLists(List<ValidationErrorDTO> singleErrors,
                                                      List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}
