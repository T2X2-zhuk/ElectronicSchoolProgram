package SchoolStudent.restAPI.microservice.schoolLessonsAndCertificates.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeleteStudentsLessonsAndCertificatesMicroserviceRequest {

    private List<Long> studentIds;
}
