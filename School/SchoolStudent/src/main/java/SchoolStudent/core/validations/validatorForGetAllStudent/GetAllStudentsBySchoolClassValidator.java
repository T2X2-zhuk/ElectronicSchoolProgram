package SchoolStudent.core.validations.validatorForGetAllStudent;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
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
class GetAllStudentsBySchoolClassValidator {

    @Autowired
    private List<GetAllStudentsBySchoolClassValidation> getAllStudentsBySchoolClassValidations;

    public List<ValidationErrorDTO> validate(GetAllStudentsBySchoolClassRequest request) {
        List<ValidationErrorDTO> singleErrors = collectSingleStudentErrors(request);
        List<ValidationErrorDTO> listErrors = collectListStudentErrors(request);
        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationErrorDTO> collectSingleStudentErrors(GetAllStudentsBySchoolClassRequest request) {
        return getAllStudentsBySchoolClassValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectListStudentErrors(GetAllStudentsBySchoolClassRequest request) {
        return getAllStudentsBySchoolClassValidations.stream()
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
