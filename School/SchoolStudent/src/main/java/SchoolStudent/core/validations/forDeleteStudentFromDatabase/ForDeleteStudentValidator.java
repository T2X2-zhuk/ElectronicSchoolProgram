package SchoolStudent.core.validations.forDeleteStudentFromDatabase;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;
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

    public List<ValidationErrorDTO> validate(DeleteStudentFromDatabaseRequest request) {
        List<ValidationErrorDTO> singleErrors = collectSingleStudentErrors(request);
        List<ValidationErrorDTO> listErrors = collectListStudentErrors(request);
        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationErrorDTO> collectSingleStudentErrors(DeleteStudentFromDatabaseRequest request) {
        return forDeleteStudentValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectListStudentErrors(DeleteStudentFromDatabaseRequest request) {
        return forDeleteStudentValidations.stream()
                .map(validation -> validation.validateList(request))
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
