package SchoolStudent.services.schoolClass;

import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.request.schoolClass.GetSchoolClassIdsRequest;
import SchoolStudent.response.schoolClass.GetSchoolClassIdsResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.schoolClass.GetSchoolClassIdsValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetSchoolClassIdsService {

    private final SchoolClassRepository repository;
    private final GetSchoolClassIdsValidator validator;

    @Transactional
    public GetSchoolClassIdsResponse execute(GetSchoolClassIdsRequest request){
        List<Long> schoolClassIds = getSchoolClassIds(request);
        List<ValidationError> errors = validator.validate(schoolClassIds);
        if (!errors.isEmpty()){
            return GetSchoolClassIdsResponse.builder()
                    .errors(errors)
                    .build();
        }
        return GetSchoolClassIdsResponse.builder()
                .studentsIds(schoolClassIds)
                .build();
    }

    private List<Long> getSchoolClassIds(GetSchoolClassIdsRequest request) {
        return request.getSchoolClassDTOS()
                .stream()
                .map(dto -> repository
                        .findByNumberAndCategory(dto.getNumber(), dto.getCategory())
                        .map(SchoolClass::getId)
                        .orElse(null)
                )
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }
}
