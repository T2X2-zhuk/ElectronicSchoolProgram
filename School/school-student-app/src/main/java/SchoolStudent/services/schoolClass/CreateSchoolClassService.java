package SchoolStudent.services.schoolClass;

import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.request.schoolClass.CreateSchoolClassRequest;
import SchoolStudent.response.schoolClass.CreateSchoolClassResponse;
import SchoolStudent.util.ValidationError;
import SchoolStudent.validations.schoolClass.CreateSchoolClassValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateSchoolClassService {

    private final CreateSchoolClassValidator validator;
    private final SchoolClassRepository repository;

    @Transactional
    public CreateSchoolClassResponse execute(CreateSchoolClassRequest request){
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()){
            return CreateSchoolClassResponse.builder().errors(validationErrors).build();
        }
        else {
            repository.save(buildSchoolClass(request));
        }
        return CreateSchoolClassResponse.builder().message("School class successful created.").build();
    }

    private SchoolClass buildSchoolClass(CreateSchoolClassRequest request){
        return SchoolClass.builder().number(request.getSchoolClassDTO().getNumber())
                .category(request.getSchoolClassDTO().getCategory()).build();
    }
}
