package SchoolStudent.core.validations.validatorForGetAllStudent;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;

import java.util.List;
import java.util.Optional;

public abstract class GetAllStudentsBySchoolClassRequestFieldsBySchoolClassValidationImpl implements GetAllStudentsBySchoolClassValidation {

    @Override
    public Optional<ValidationErrorDTO> validate(GetAllStudentsBySchoolClassRequest request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(GetAllStudentsBySchoolClassRequest request) {
        return null;
    }

}
