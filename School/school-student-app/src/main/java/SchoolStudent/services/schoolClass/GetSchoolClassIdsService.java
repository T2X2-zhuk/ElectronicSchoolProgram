package SchoolStudent.services.schoolClass;

import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.request.GetSchoolClassIdsRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.response.GetSchoolClassIdsResponse;
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

    @Transactional
    public GetSchoolClassIdsResponse execute(GetSchoolClassIdsRequest request){
        List<Long> schoolClassIds = request.getSchoolClassDTOS()
                .stream()
                .map(dto -> repository
                        .findByNumberAndCategory(dto.getNumber(), dto.getCategory())
                        .map(sc -> sc.getId())
                        .orElse(null)
                )
                .filter(Objects::nonNull)
                .distinct()
                .toList();
        return GetSchoolClassIdsResponse.builder()
                .studentsIds(schoolClassIds)
                .build();
    }
}
