package SchoolStudent.core.validations.validatorForGetAllStudent.fieldForValidateParameterGetAllStudentsRequest;

import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.validations.ValidationErrorFactory;
import SchoolStudent.core.validations.validatorForGetAllStudent.GetAllStudentsBySchoolClassRequestFieldsBySchoolClassValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FieldNumberClassForGetAllStudentsValidation extends GetAllStudentsBySchoolClassRequestFieldsBySchoolClassValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Autowired private SchoolClassRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(GetAllStudentsBySchoolClassRequest request) {
        if (mustNotBeEmpty(request).isPresent()){
            return mustNotBeEmpty(request);
        }if (notSuchSchoolClass(request).isPresent()){
            return notSuchSchoolClass(request);
        }
        return Optional.empty();
    }

    private Optional<ValidationErrorDTO> mustNotBeEmpty(GetAllStudentsBySchoolClassRequest request){
        return (request.getNumber() == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }


    private Optional<ValidationErrorDTO> notSuchSchoolClass(GetAllStudentsBySchoolClassRequest request){
        return (repository.findAllNumberWhichEqualsThisParameter(request.getNumber()).isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }
}
