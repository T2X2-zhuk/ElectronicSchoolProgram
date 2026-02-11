package SchoolLessonsAndCertificates.restAPI.microservice.schoolStudent.request;

import SchoolLessonsAndCertificates.jpa.dto.SchoolClassDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSchoolClassIdsRequest {

    private List<SchoolClassDTO> schoolClassDTOS;
}
