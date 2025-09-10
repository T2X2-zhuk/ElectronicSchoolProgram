package SchoolStudent.core.validations.student;

import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.student.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.validations.student.Get.GetStudentsValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public
class GetStudentsValidator {

    @Autowired
    private List<GetStudentsValidation> getStudentsValidations;

    public List<ValidationErrorDTO> validate(GetAllStudentsBySchoolClassRequest request) {
        return collectListStudentErrors(request);
    }

    private List<ValidationErrorDTO> collectListStudentErrors(GetAllStudentsBySchoolClassRequest request) {
        return getStudentsValidations.stream()
                .map(validation -> validation.validationErrorDTOSList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }



}
