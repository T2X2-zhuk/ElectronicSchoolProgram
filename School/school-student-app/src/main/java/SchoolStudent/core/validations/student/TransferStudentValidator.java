package SchoolStudent.core.validations.student;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.validations.student.Transfer.TransferStudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public
class TransferStudentValidator {

    @Autowired
    private List<TransferStudentValidation> transferStudentValidations;

    public List<ValidationErrorDTO> validate(TransferOfStudentToANewClassRequest request) {
        return collectListStudentErrors(request);
    }

    private List<ValidationErrorDTO> collectListStudentErrors(TransferOfStudentToANewClassRequest request) {
        return transferStudentValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
